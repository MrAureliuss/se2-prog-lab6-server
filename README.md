# Lab6-server
> Жить так сладко и весело когда есть бабкина пенсия.
>
**В данном репозитории находится исходный код серверной части шестой лабораторной работы. [Код клиента](https://github.com/Vsev0l0d/se2-prog-lab6-client). В основе лежит [5 лабораторная работа](https://github.com/MrAureliuss/Lab5/).**


## Содержание <a name="Содержание"></a> 
* [Задание лабораторной работы.](#Задание)
* [Нововведения](#Нововведения)
* [Объяснение основных моментов кода серверной части.](#Пояснения)
* [Заключение.](#Заключение)

### Задание лабораторной работы <a name="Задание"></a>
![Alt-текст](https://i.imgur.com/7Cf9GwG.jpg)
![Alt-текст](https://imgur.com/NOn07t5.jpg)
[Содержание :arrow_up:](#Содержание)

### Нововведения <a name="Нововведения"></a>
+ Величайшое армяно-русское изобретение в виде [сериализованых команд](/src/Commands/SerializedCommands). Подробнее в главе [Объяснение основных моментов кода серверной части.](#Пояснения)
+ [Контроллер, он же основной принимающий класс соединений.](/src/ServerSocket/Controller.java)
+ Злой принимающий спросил за валидацию данных еще и на сервере? Не беда, [валидаторы](/src/Utils/Validator.java) спасут тебе баллы!
+ Вот же он сериализованный объект в виде байтов. Стоп.. А как их прочитать? Да как как, специальными и секретными [декрипторами.](/src/Utils/CommandHandler/Decrypting.java)
+ serialVersionUID в классах для создания будущих сериализованных объектов. Подробнее в главе [Объяснение основных моментов кода серверной части.](#Пояснения) 
+ Убран инвокер, так как на сервере он не нужен(нет считывания с консоли).
+ Логгер(о нужных библиотеках для работы логгера в [заключении](#Заключение))

[Содержание :arrow_up:](#Содержание)

### Объяснение основных моментов кода серверной части. <a name="Пояснения"></a>

+ Запуск программы как обычно начинается [из метода main класса Main](/src/Main.java). На этом все с вами были MrAurelius и Vsev0l0d. Не забывайте ставить нам звездочки на гитхабе и комментить нас в твиттере которого у нас нет, всем хорошего настроения. Пока!
+ Да я понял, что ты понял, что это троллинг. Продолжаем. Тебя жесткогокодера228, возможно, заинтересовало, что это за строчка такая в мейне   
```Java
Runtime.getRuntime().addShutdownHook(new Thread(ParserJson::collectionToJson));

```
Это так называемый "хук", который при закрытии приложения создает новый поток(Thread), записывающий коллекцию(c его объектами на момент закрытия) в файл json.
+ Далее из мейна у нас вызывается метод run контроллера
```Java
Controller controller = new Controller();
controller.run(args[0]); // args[0] - считываемый из консоли порт для прослушивания.
```
+ В самом [Контроллере](/src/ServerSocket/Controller.java) ничего по-факту интересного. Мы просто пытаемся считать порт, введеный в консоль при запусе jar файла и если порт не соответстует требованиям(например состоит из букв), то работа прекращается.
Больше всего нас интересуют строки
```Java
while (true) {   // Работаем пока нас не отключат
    clientSocket = server.accept();   // Получаем присоединившегося клиента
    logger.info("А я все думал, когда же ты появишься: " + clientSocket);  // Сообщаем о нем.
    try { 
        while (true) {  // Работаем с клиентом пока он не сбежит в страхе
            in = new ObjectInputStream(clientSocket.getInputStream());  // Создаем входной поток объектов данного пользователя.
            Decrypting decrypting = new Decrypting(clientSocket);  // Создаем объект декриптора.
            Object o = in.readObject(); // Считываемм поток байтов(будущий сериализованый объект).
            decrypting.decrypt(o);  // Декриптим поток байтов пришедший с клиента.
        
    } catch (EOFException | SocketException ex) {  // Ловим бегство пользователя и сигнализируем это.
        logger.info("Клиент " + clientSocket + " того, откинулся...");
    } finally {
        clientSocket.close();  // Закрываем соединение с этим пользователем.
        if (in != null) { in.close(); }  // Если вдруг клиента никогда и не было, делаем проверку, чтоб не прострелить колено.
    }
}
```

+ Клиент и сервер находятся в обмене данными, эти данные передаются посредством [сериализованных команды](/src/Commands/SerializedCommands).
Мы выделили их в 5 типов:
1. SerializedSimplyCommand - класс для сериализации простых команд(в которых нам нужен только объект самой конкретной команды). Например clear.
2. SerializedArgumentCommand - класс для сериализации команд с аргументом.
3. SerializedObjectCommand - класс для сериализации команд с объектами. Например add.
4. SerializedCombinedCommand - класс для сериализации комбинированных команд(с объектом и аргументом). Нужен только в update.
5. SerializedMessage - класс, который несет в себе обычное текстовое сообщение. Используется для уведомлений.

+ Получив одну из сериализованных команд, мы должны определить ее тип, делается это в [декрипторе](/src/Utils/CommandHandler/Decrypting.java)
```Java
if (o instanceof SerializedArgumentCommand) {  // Проверка на причастность к одной из сериалованных команд.
    SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o; // Приводим типы.
    Command command = argumentCommand.getCommand(); // Получаем команду.
    String arg = argumentCommand.getArg(); // Получаем аргумент.
    command.execute(arg, socket);  // Вызываем конкретный класс команды. Внимание! Абстрактный класс команды изменен, не поленись зайди и посмотри что там изменилось.
}
```

+ После экзекюта команды, мы попадаем в соответствующий метод [ресивера](/src/Commands/CommandReceiver.java)
```Java
public void clear() throws IOException {
    CollectionManager.clear();  // Делаем нужную работу через менеджер коллекции.
    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream(  // Создаем выходной поток объектов для клиента. 
    out.writeObject(new SerializedMessage("Коллекция успешно очищена."));  // Шлем сообщение на клиент.
    logger.info(String.format("Клиенту %s:%s отправлен результат работы команды CLEAR", socket.getInetAddress(), socket.getPort())); // Логгируем
}
```

+ serialVersionUID. В репозитории клиента вы можете увидеть, что исходные коды сериализуемых команд на клиенте и сервере - различны. Чтобы иметь возможность менять исходные коды файлов на клиенте и сервере, мы и добавляем этот тег в нужные классы. Более подробно можно прочитать в интернете.

[Содержание :arrow_up:](#Содержание)

### Заключение <a name="Заключение"></a>
Подведем итог, сервер работает по принципу ~~нажрался и спит~~:
1. Просыпается и ждет клиентов.
2. При получении какого-либо объекта десериализует его посредством декрипторов.
3. Меняет состояние коллекции(удаляет, добавляет) или выводит информацию о ней(когда создана, объекты в ней и тд)
4. Отправляет клиенту сообщение с результатом работы.

Проанализировав процесс жизнедеятельности сервера, я пришел к выводу, что мой сервер является ~~проституткой~~ печенегом.

**Либы для работы логгера(ставятся при помощи мавена из интельки):**


**1. org.slf4j:slf4j-api:1.7.25**

**2. ch.qos.logback:logback-classic:1.2.3**

**Как обычно из неприятного можно выделить тот факт, что тебе придется с этим еще самому немного поразбираться.**

Если:
1. У тебя остались какие-то вопросы, на которые ты все же не смог ответить в процессе разборки лабы
2. Ты нашел неточность в лабе или описании лабы 
3. Хочешь сказать мне спасибо (зачем это делать?) 
4. Послать меня
5. Сказать, что я говнокодер  
то, можешь написать мне в [ВК](https://vk.com/eriksimohyan) или соразработчику этой шайтан-машины [ВК](https://vk.com/mind_blowing_blow_job)
6. Данный исходный код помог тебе, то не поленись и нажми сверху на звездочку, дабы поднять мой ЧСВ и дать мне понять, что я хоть кому-то помог.
7. Ты шикарная девушка в рассвете сил жаждующая того самого ненаглядного самца, то писать все тому же соразработчику [СЕКС-БОМБЕ](https://vk.com/mind_blowing_blow_job)

**Спасибо всем за свечки, если кто не знал, то я сдал физику! Еще правда второй сем, но это совершенно другая история**

**Все.**  

[Содержание :arrow_up:](#Содержание)
