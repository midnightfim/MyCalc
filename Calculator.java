import java.util.Stack;

/* Реализуем класс для работы со строкой
 *
 * Используем метод записи - Обратная польская нотация(RPN)*/


public class Calculator {




    private static String ExpressionToRPN(String expr){    // Метод для реализации перевода строки в RPN

        /*Для реализации этого метода нам нужно завести строку для конечного результата и очередь:
        *
        * 1) Проверяем каждый символ в подающемся выражении
        * 2) Если идет число, мы записываем его в строку
        *   Если идут знаки +-/*, мы добавляем пробел в строку и проверяем какой приоритет у знака
        *       Если приоритет верхнего символа в очереди меньше чем приоритет идущего знака
        *           Записываем идущий знак в очередь
        *       Если приоритет верхнего символа в очереди больше чем приоритет идущего знака
        *           Перепимываем знаки с большим приоритеом из очереди в строку
        *           Записываем идущий знак в очередь
        *    Если идут скобки
        *       ( - просто записываем в очередь
        *       ) - перепимываем знаки из очереди в строку до тех пор, пока не встретим "("
        * 3) Строка на выходе - строка в RPN
        */

        String current = "";
        Stack<Character> stack = new Stack<>();
        int priority;


        for (int i = 0; i < expr.length(); i++) {
            priority = getPriority(expr.charAt(i));
            if (priority == 0)
                current += expr.charAt(i);
            if (priority == 1)
                stack.push(expr.charAt(i));
            if (priority > 1) {
                current += ' ';
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) current += stack.pop();
                    else break;
                }
                stack.push(expr.charAt(i));
            }
            if (priority == -1) {
                current += ' ';
                while (getPriority(stack.peek()) != 1) {
                    current += stack.pop();
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            current += stack.pop();
        }
        return current;

    }

    private static double RPNtoAnnswer(String rpn){    // метод для получения ответа из RPN

        /* Для реализации этого метода нам нужно завести вспомогательную строку и очередь:
        *
        * 1) Проверяем каждый символ в подающемся выражении
        * 2) Если идет число - добавляем его в очередь
        *   Если идет знак
        *       Производим математическое действие, которое этот знак описывет над последними 2-я числами в очереди
        *       Записывем результат в очередь
        * 3) В конце возвращаем единственное число в очереди, которое является нашим ответом
        */



        String operand = new String();
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < rpn.length(); i++){
            if (rpn.charAt(i) == ' ') continue;
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand));
                operand = new String();
            }
            if (getPriority(rpn.charAt(i)) > 1 ) {
                double a = stack.pop();
                double b = stack.pop();
                if (rpn.charAt(i) == '+') stack.push(b+a);
                if (rpn.charAt(i) == '-') stack.push(b-a);
                if (rpn.charAt(i) == '/') stack.push(b/a);
                if (rpn.charAt(i) == '*') stack.push(b*a);
            }
        }
        return stack.pop();
    }

    private static int getPriority(char token){     //   приоритеты символов
        if (token == '*' ||token == '/') return 3;
        else if (token == '+' ||token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        else return 0;
    }

    public static String calcAnswer(String userExpr){  // метод для прохождения всего цикла String - RPN - double - String
        try {
            return Double.toString(RPNtoAnnswer(ExpressionToRPN(userExpr)));
        }catch(Exception e){
            return "Please enter 0-9 and +-*/.)(";
        }
    }
}
