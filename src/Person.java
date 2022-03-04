public class Person {
    String name;
    double money;
    public Person(String pName, double pMoney){
        name = pName;
        money = pMoney;
    }
    public void addMoney(double x){
        money += x;
    }
    public void subtractMoney(double x){
        money -= x;
    }
}
