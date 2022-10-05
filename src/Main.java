import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScannerException, SingException, RomeNumberException, NegativeNumberRome, NumberFormatException, NumFormException, RangeRomeException {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите арифметическую операцию:");
        String mathOperation = in.nextLine();
        String sing = checkSing(mathOperation);                                            //Проверяю наличие математического символа

        if(sing.isEmpty()) {
            throw new ScannerException("Строка не является математической операцией");     //Выбрасываю исключение в случае отсутствия какого-либо математического символа
        }

        String[] numArray = splitStr(mathOperation, sing);                                 //Разбиваю строку по операнду для выборки чисел из нее

        String repeatSing = checkSing(numArray[1]);                                        //
        if(numArray.length > 2 || !repeatSing.isEmpty()){                                  //Проверяю наличие присутствия еще какого либо операнда
            throw new SingException("Вы использовали более одного операнда");              //и в случае его нахождения выбрасываю исключение
        }                                                                                  //


        numArray[0] = numArray[0].replace(" ", "");                        //Удалаю пробелы из 1-го числа, если имеются
        numArray[1] = numArray[1].replace(" ", "");                        //Удалаю пробелы из 2-го числа, если имеются


        Rome numRome = new Rome();                                                         //Создаю объект класса для работы с римской системой исчисления
        numRome.setNumRom(numArray);                                                       //Передаю в class Rome массив для дальнейшей инициализации цифр
        if(numRome.getCheckNumOne()[0] && numRome.getCheckNumOne()[1]) {                   //Произвожу проверку все ли числа оказались римскими
            String operation = String.valueOf(numRome.getNumbers()[0])+String.valueOf(sing)+String.valueOf(numRome.getNumbers()[1]);//Объединяю строку для последующей передачи в метод public static String calc(String input)
            String result = calc(operation);
            if(Integer.parseInt(result) <= 0) {                                            //Проверяю не является ли результат вычислений отрицательным
                throw new NegativeNumberRome("В римской системе исчисления нет отрицательных чисел");
            } else {
                System.out.println(numRome.getResult(result));                             //Инициализирую значение аррабской цифры в римской системе исчисления и вывожу результат(Конец логического блока, программа завершится)
            }
        }
        else if(!numRome.getCheckNumOne()[0] && numRome.getCheckNumOne()[1] || numRome.getCheckNumOne()[0] && !numRome.getCheckNumOne()[1]) { //Проверяю не используются ли римская и аррабская системы исчисления одновременно
            throw new RomeNumberException("Нельзя использовать одновременно разные системы исчисления");
        }
        else {                                                                             //Если цифры оказались аррабскими, то дальше начну их проверку на корректность

            boolean checkNumFormat = true;
            int a = 0;
            int b = 0;

            try {                                                                          //
                a = Integer.parseInt(numArray[0]);                                         //
                b = Integer.parseInt(numArray[1]);                                         //
            } catch (NumberFormatException e){                                             //Запустил блок try-catch для проверки корректности введеных цифр
                checkNumFormat = false;                                                    //
            }                                                                              //
            if(checkNumFormat) {
                if(a > 10 || b > 10 || a < 1 || b < 1){                                    //
                    throw new RangeRomeException("Вы вели число больше 10");               //Проверяем, находятся ли введенные числа в необходимом диапозоне
                }                                                                          //
                String operation = String.valueOf(a) + String.valueOf(sing) + String.valueOf(b);//Объединяю строку для последующей передачи в метод public static String calc(String input)
                System.out.println(calc(operation));                                       //Передача математической операции и вывод результата(Конец логического блока, программа завершится)
            } else {throw new NumFormException("Вы ввели не соответсвующие числа");}
        }

    }

    public static String calc(String input) {
        String sing = checkSing(input);                         //Проверяем какой математический операнд у переданной математической операции
        String[] numArray = splitStr(input, sing);              //Разбваем строку на массив строк п ооперанду
        int a = Integer.parseInt(numArray[0]);                  //Преобразовываем первое строчное значение массива в число
        int b = Integer.parseInt(numArray[1]);                  //Преобразовываем второе строчное значение массива в число
        String result = String.valueOf(mathRes(a, b, sing));    //Совершаем математическую операцию и преобразуем ответ в строку

        return result;
    }

    public static String checkSing(String mathOprtn){
        /*
        Метод для проверки наличия математического символа в строке.
        Здесь я создал массив с математическими операндами и запустил цикл в цикле,
        сравнивая, поочередно, символ строки с каждым символом из массива
        и при первом же совпадении присваиваю операнд строке и выхожу из циклов.
        */
        String[] mathSing = {"+","-","/","*"};
        String sing = new String();
        boolean checkIter = false;

        for(int i = 0; i < mathOprtn.length(); i++){
            for(int j = 0; j < mathSing.length; j++){
                String singCheck = String.valueOf(mathOprtn.charAt(i));

                if(singCheck.contains(mathSing[j])) {
                    sing = mathSing[j];
                    checkIter = true;
                    break;
                }
            }
            if(checkIter){break;}
        }
        return sing;

    }

    public static String[] splitStr(String mathOprtn, String sing) {
        /*
        Метод разбивает строку на массив строк посредством операнда,
        который присутсвует в введеной математической операции.
        */
        String[] array = new String[0];

        switch (sing){
            case "+":
                array = mathOprtn.split("\\+");
                break;
            case "*":
                array = mathOprtn.split("\\*");
                break;
            case "/":
                array = mathOprtn.split("/");
                break;
            case "-":
                array = mathOprtn.split("-");
                break;
        }

        return array;
    }

    public static int mathRes(int a, int b, String sing){
        /*
        Метод производит математические вычисления
        по четырем операциям:
        + сложение
        - вычитание
        * умножение
        / деление
        */
        int mathRes = 0;

        switch (sing){
            case "+":
                mathRes = a + b;
                break;
            case "*":
                mathRes = a * b;
                break;
            case "/":
                mathRes = Math.round(a / b);    //Округляю до целлого числа
                break;
            case "-":
                mathRes = a - b;
                break;
        }
        return mathRes;
    }

}

class Rome {
    /*
    Класс создан для работы с римской системой исчисления,
    а именно для перевода арабских числе в римские и обратно,
    для совершения над ними математических операций
    */
    private String[] numRom = new String[0];
    private int[] numbers = {0,0};                          //Массив чисел для записи аррабского значения римской цифры
    private boolean[] checkLogic = {false,false};           //Логические флажки для будующей проверки, являются ли оба числа римскими
    private String result;
    public void setNumRom(String[] numRom) {
        this.numRom = numRom;                               //принимаю массив строк для последующей работы с ним
    }

    public boolean[] getCheckNumOne() throws RangeRomeException {
        /*
        Метод для преобразования римских чисел в аррабские
        посредством обращения к enum RomNum
        */
        for(RomNum d: RomNum.values()){
            String g = String.valueOf(d);
            for(int i = 0; i < numRom.length; i++) {
                String q = numRom[i];
                if(q.equals(g)) {
                    if(d.getValue() > 10){                                        //
                        throw new RangeRomeException("Вы вели число больше 10");  //Произвожу проверку, не являеются ли введеные числа больше 10
                    }                                                             //
                    checkLogic[i] = true;            //Как только нахожу совпадение выставляю флажок говорящий о том что цифра найдена
                    numbers[i] = d.getValue();       //Записываю цифру
                }
            }
        }
        return checkLogic;
    }

    public int[] getNumbers(){
        return numbers;             //Возвращаю массив строк с аррабскими числами, которые соответвуют значением римских
    }
    public String getResult(String result){
        /*
        Это метод преобразует ответ обратно в римскую систему исчисления из аррабской
        */
        for(RomNum d: RomNum.values()){
            if(Integer.parseInt(result) == d.getValue()){
                this.result = String.valueOf(d);
                break;
            }
        }

        return this.result;
    }

}
