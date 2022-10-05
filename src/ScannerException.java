/*
Класс с Исключениями, не знаю, как правильно еще было создать исключения, но я решил просто здесь их указать и вызывать по мере надобности.
*/
class ScannerException extends Exception {          //Выбрасываю исключение в случае отсутствия какого-либо математического символа
    public ScannerException(String description){
        super(description);
    }
}
class SingException extends Exception {             //Выбрасываю исключение в случае использования более одного операнда
    public SingException(String description){
        super(description);
    }
}
class RomeNumberException extends Exception {       //Выбрасываю исключение в случае использования одновременно разных системы исчисления
    public RomeNumberException(String description){
        super(description);
    }
}
class NegativeNumberRome extends Exception {        //Выбрасываю исключение в случае отрицательного результата для римских цифр
    public NegativeNumberRome(String description) {
        super(description);
    }
}
class NumFormException extends Exception{           //Выбрасываю исключение в случае если введенные данные не удовлетворяют ТЗ
    public NumFormException(String description) {
        super(description);
    }
}
class RangeRomeException extends Exception {        //Выбрасываю исключение в случае если введеные числа больше 10
    public RangeRomeException(String description) {
        super(description);
    }
}
