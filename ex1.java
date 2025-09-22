import java.util.Scanner;
public class ex1{
public static void main(String[] args){
Scanner scan = new Scanner(System.in);
System.out.print("Enter the name of the consumer: ");
String name=scan.nextLine();
System.out.print("Enter the consumer number: ");
int cnum=scan.nextInt();
scan.nextLine();
System.out.print("Enter the previous month reading: ");
double prevread=scan.nextDouble();
scan.nextLine();
System.out.print("Enter the current month reading:");
double currread=scan.nextDouble();
scan.nextLine();
System.out.print("Enter the connection type (type 1 for Domestic |type 2 for  Commercial) : ");
int type=scan.nextInt();
scan.nextLine();
double amount=0;
double read = currread-prevread;
if(type==1){
String ctype="Domestic";
if(read >= 0 && read <= 100){
amount=read*1;
}
else if(read >=101 && read<=200){
amount=read*2.50;
}
else if (read >=201 && read<=500){
amount=read*4;
}
else if(read >= 501){
amount=read*6;
}
else{
System.out.println("Invalid reading!");
}
}
else if(type==2){
String ctype="Commericial";
if(read>=0 && read<=100){
amount=read*2;
}else if (read>=101 && read<=200){
amount= read*4.50;
}
else if (read>=201 && read<=500){
amount= read*6;
}
else if (read>=501){
amount=read*7;
}
else{
System.out.println("Invalid reading!");
}
}
else{
System.out.println("Invalid type please check again!");
return;
}
System.out.println("\n---Electricity Bill---");
System.out.println("Consumer Name:"+name);
System.out.println("Consumer No:"+cnum);
System.out.println("Current month reading:"+currread);
System.out.println("Previous month reading:"+prevread);
System.out.println("Bill Amount for "+read+" units is:"+amount);
scan.close();
}
}
