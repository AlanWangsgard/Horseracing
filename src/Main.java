import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Horse steve = new Horse("steve", 1, "brown", 3);
        Horse jim = new Horse("jim", 2, "White", 4);
        Person mike = new Person("mike", 100);

        ArrayList<Bet> bets = new ArrayList<Bet>();
        ArrayList<Horse> horses = new ArrayList<Horse>();

        horses.add(jim);
        horses.add(steve);

        Random rand = new Random();
        boolean valid = true;
        while (valid) {
            System.out.println("Welcome to the races!");
            System.out.println("1. Place a bet");
            System.out.println("2. Register a Horse");
            System.out.println("3. Start the race!");
            System.out.println("q. Quit");
            Scanner sc = new Scanner(System.in);
            String option = sc.nextLine();
            System.out.println(option);
            if (option.equals("q")) {
                valid = false;
            } else if (option.equals("1")) {
                System.out.println("Horse Name");
                String HorseName = sc.nextLine();
                System.out.println("Bet amount");
                int amount = sc.nextInt();
                Bet playerBet = new Bet(HorseName, amount);
                bets.add(playerBet);
                for (Bet bet : bets) {
                    System.out.println(bet.betAmount + " " + bet.horseName);
                }
            } else if (option.equals("2")) {
                System.out.println("Horse Name");
                String HorseName = sc.nextLine();
                System.out.println("Horse Color");
                String Hcolor = sc.nextLine();
                System.out.println("Horse Number");
                int Hnumber = sc.nextInt();
                System.out.println("Stamina");
                double Hstamina = sc.nextDouble();
                Horse horse = new Horse(HorseName, Hnumber, Hcolor, Hstamina);
                horses.add(horse);
                for (Horse hoe : horses) {
                    System.out.println(hoe.name + " " + hoe.number + " " + hoe.color + " " + hoe.stamina);
                }
            } else if (option.equals("3")) {
                for (Horse horse : horses){
                    horse.position = 0;
                }
                double leadPosition = 0;
                String leadHorse = "";
                boolean racing = true;
                while (racing) {
                    for (Horse horse : horses) {
                        horse.position += rand.nextDouble(2);
                        System.out.println(horse.name + " " + horse.position);
                        if (horse.position > leadPosition) {
                            leadPosition = horse.position;
                            leadHorse = horse.name;
                        }
                    }
                    if (leadPosition >= 10){
                        racing = false;
                        System.out.println("The winnning Horse is " + leadHorse);
                        for (Bet bet : bets){
                            if(bet.horseName.equals(leadHorse)){
                                mike.money += bet.betAmount*1.5;
                                System.out.println("You chose the right horse! you now have $" + mike.money);
                            }else {
                                mike.money -= bet.betAmount;
                                System.out.println("You chose the wrong horse. you now have $" + mike.money);
                            }
                        }
                    }else{
                        System.out.println("The leading horse is " + leadHorse);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        System.out.println("got interrupted!");
                    }
                }
                bets.clear();

            }
            if (mike.money <= 0){
                valid = false;
                System.out.println("you have no money and must leave the track.");
            }
        }
    }
}
