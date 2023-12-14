import java.util.Scanner;

class Calc {
    public static void main(String[] args) throws Exception {
        System.out.println("Калькулятор");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите выражение (арабские или римские): ");
        String expression = scanner.nextLine();
        System.out.println(calc(expression));
    }

    private static String calc(String expression) throws Exception {
        boolean isRoman = false;
        int a, b, arabian, res = 0;
        String resArabian = "";
        String returnRes = "";
        expression = expression.trim().replace(" ","");


        // Сколько ввел операндов пользователь
        String [] expressionArr = expression.split("[+\\-*/]");
        if(expressionArr.length != 2) {
            throw new Exception("Должно быть два операнда");
        }


        // Проверка на знак
        String sign = toCheckSign(expression);
        if(sign == null){
            throw new Exception("Вы ввели неверный знак");
        }

        //Проверка на числа(Пользователь может ввести бувку/слово вместо числа)
        for(int i = 0; i < expressionArr.length; i++){
            if(!isNumeric(expressionArr[i])){
                throw new Exception("Ввели некорректное выражение");
            }
        }

        // Проверяем, чтобы все числа были одного формата (Арабские или Римские), а также конвентируем римские в арабские для решения
        if(Roman.isRoman(expressionArr[0]) && Roman.isRoman(expressionArr[1])){
            a = Roman.convertToArabian(expressionArr[0]);
            b = Roman.convertToArabian(expressionArr[1]);
            isRoman = true;
        } else if(!Roman.isRoman(expressionArr[0]) && !Roman.isRoman(expressionArr[1])){
            a = Integer.parseInt(expressionArr[0]);
            b = Integer.parseInt(expressionArr[1]);
            isRoman = false;
        } else {
            // Если одно число римское, а другое арабское
            throw new Exception("Все числа должны быть в одном формате");
        }

        // Решение выражения
        switch (sign){
            case "+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "*":
                res = a * b;
                break;
            case "/":
                res = a / b;
                break;
            default:
                throw new Exception("Ошибка в выражении");
        }
        if(isRoman == false){
            if(a > 10 || b > 10){
                throw new Exception("Числа должны быть от 1 до 10");
            }
            returnRes = String.valueOf(res);

        } else{
            if(a > 10 || b > 10){
                throw new Exception("Числа должны быть от I до X");
            } else {
                if (isRoman) {
                    //если римское число получилось меньше либо равно нулю, генерируем ошибку
                    if (res <= 0) {
                        throw new Exception("Римское число должно быть больше нуля");
                    } else {
                        //конвертируем результат операции из арабского в римское
                        resArabian = Roman.convertToRoman(res);
                        //Конвертируем арабское число в тип String
                        returnRes = resArabian;
                    }
                }
            }
        }

        return "Ответ: " + returnRes;
    }
    private static String toCheckSign(String expression) {
        if(expression.contains("+")){
            return "+";
        } else if(expression.contains("-")){
            return "-";
        } else if(expression.contains("*")){
            return "*";
        } else if(expression.contains("/")){
            return "/";
        } else {
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}
