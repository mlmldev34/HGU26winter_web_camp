package org.example;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    static void main() {
        try {
            Class<?> dbDriver = Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("마리아디비 드라이버(" + dbDriver.toString() + ")가 로딩됨");
        } catch (ClassNotFoundException e) {
            System.out.println("마리아디비 드라이버가 로딩되지 않음");
            e.printStackTrace();
        }
    }
}
