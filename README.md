# MyTaskForNetcracker
My task to get a job at Netcracker

Тестовое задание
Реализовать систему конфигурации и запуска тестовых сценариев.
•	Требования к системе:
1.	Система должна получать на вход файл с описанием сценариев ( например xls, xml, json и т.п), в котором описаны шаги и их параметры.
2.	Система должна прочитать этот файл и выполнить шаги указанные в нем.
3.	Необходимо реализовать поддержку как минимум 5 шагов:
a.	- openUrl – открываетbrowser и переходит на указанный URL
b.	- click – Выполняет нажатие элемента на странице (в качестве параметра принимает локатор элемента, например xpath)
c.	- setValue – записывает значение в текстовое поле (в качестве параметров принимает локатор элемента и текст который нужно ввести)
d.	- checkElementVisible – проверяет, что элемент отображается на странице (в качестве параметра принимает локатор элемента, например xpath)
e.	- screenshot – выполняет снимок экрана

•	Требования к выполнению:
a.	используемый язык Java 
b.	сборка проекта Maven
c.	используемый браузер Chrome
d.	Работа с браузером осуществляется через библиотеку selenium (можно заменить библиотекой Selenide)

Пример конфигурационного файла:
Action	Params	Description
openUrl	https://google.com
Open strikepackage.Browser
Click	//*[@id="sb_ifc0"]	Click Element
setValue	//*[@id="sb_ifc0"] | Netcracker	Set Text Value
Click	//*[@id="tsf"]/div[2]/div[3]/center/input[1]	Click Search Button
Click	//*[@id="rso"]/div[1]/div/div/div/h3/a	Click First Result
Screenshot		Take screenshot

