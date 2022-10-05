public enum RomNum {
    /*
    Числа указаны согласно возожным ответам
    */
    I(1),II(2),III(3),IV(4),V(5),VI(6),VII(7),IIX(8),IX(9),
    X(10),XI(11),XII(12),XIII(13),XIV(14),
    XV(15),XVI(16),XVII(17),XVIII(18),XIX(19),
    XX(20),XXI(21),XXIV(24),XXV(25),XXVII(27),XXVIII(28),
    XXX(30),XXXV(35),XXXII(32),XXXVI(36),
    XL(40),XLII(42),XLV(45),XLVIII(48),XLIX(49),
    L(50),LIV(54),LVI(56),
    LX(60),LXIII(63),LXIV(64),
    LXX(70),LXXII(72),
    LXXX(80),LXXXI(81),
    XC(90),
    C(100);

    private int value;

    RomNum(int value) {this.value = value;}

    public int getValue() {
        return value;
    }
}
