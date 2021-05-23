package com.valhallabank.atm;


import java.util.*;

/**
 * ATM ÖDEV TANIMI
 *
 * Bu ödev terminal/Konsol Uygulaması olacaktır.
 *
 * "KurumsalMusteri" adlı bir sınıf yapınız ve içinde
 * Tc Kimlik No Ad Soyad Şirket Adı Hesap Bakiyesi (Bankada bulunan para miktarı)
 * *İpucu: OOP'u uygulamayı unutmayın
 *
 * "BireyselMusteri" adlı bir sınıf yapınız ve içinde
 * Tc Kimlik No Ad Soyad Ev Adresi Hesap Bakiyesi (Bankada bulunan para miktarı)
 * *İpucu: OOP'u uygulamayı unutmayın
 *
 * 5 Kişinin hesap bilgileri olacak ve bu bilgiler uygulama içinde bir Collection'da tutulacak:
 * Banka Müşteri No (string) ve Musteri (class)
 * *Hint: hangi collection tipi buna uygun iyi düşünün
 * Müşterinin bakiye bilgilerini ve ad soyad gibi bilgilerini farazi verebilirsiniz.
 *
 * Uygulama ayağa kalkınca kondola girilen banka müşteri numarasına göre ilgili kişinin
 * bakiye, adres gibi tüm bilgilerini hatta kurumsal müşteri ise
 * şirket adı gibi o tipe ait tüm bilgileri konsola basmanız ve bilgilendirmeniz istenmektedir.
 * *Hint: Scanner nedir araştırınız
 *
 * Bu bilgi verildikten sonra terminalden rakam girişi alınmalı ve
 * 1 değeri girilirse para yatırma 2 değeri girilirse para çekme işlemi başlatılmalıdır.
 *
 * Para yatırma ve çekme işlemleri için özel bir sınıf yazınız ve bu sınıftaki metodlarda
 * işlem yaptırıp sonucu ilgili değere atayınız.
 *
 *
 */
public class Atm {
    private static int totalClients = 5;
    private static final String WITHDRAW_MONEY = "1";
    private static final String DEPOSIT_MONEY = "2";
    private static boolean atmOn = true;

    public static void main(String[] args) {

        //Müşteriler oluşturulmuştur.
        Client client1 = new CorporateClient("11111", "Grim Stormborn", 5000.0, "Storm Factory");
        Client client2 = new IndividualClient("22222", "Elanaril Silverleaf", 3800.50, "Silverspring Forest");
        Client client3 = new CorporateClient("33333", "Morrok Wallinder", 63400.75, "Taborea Heros");
        Client client4 = new IndividualClient("44444", "Geralt the Witcher", 9000.0, "Rivia");
        Client client5 = new CorporateClient("55555", "Bruce Wayne", 99999999999.99, "WayneCorp");

        //LinkedList kullanmak için hesap numarası listesi oluşturulmuştur.
        LinkedList<String> clientNoList = new LinkedList<>();
        for (Integer i = 0; i < totalClients; i++){
            clientNoList.add("00"+(i+1));
        }
        // Hesap No'ları görüntülemek için
        System.out.println("Client NOs for admin : " + clientNoList);

        //Hesap numarası ile müşteriler bağlanıp(Map) listelenmiştir.
        HashMap<String, Client> clientMap = new HashMap<>();
        clientMap.put(clientNoList.get(0), client1);
        clientMap.put(clientNoList.get(1), client2);
        clientMap.put(clientNoList.get(2), client3);
        clientMap.put(clientNoList.get(3), client4);
        clientMap.put(clientNoList.get(4), client5);

        //Terminalden veri almak için scanner yaratılmıştır.
        Scanner scanner = new Scanner(System.in);

        //Giriş işlemleri

        while (atmOn) {
            Client user = loginScreenOperationsEntry(scanner, clientMap, clientNoList);

            //Para işlemleri
            if (user != null) {
                MoneyOperationsAtm moneyOperationsAtm = new MoneyOperationsAtm();
                moneyOperationsEntry(scanner, moneyOperationsAtm, user);
            }
            else {
                System.out.println("Invalid Client No\n" +
                        "--------------------------------------------");
            }
        }
    }

    public static Client loginScreenOperationsEntry (Scanner scanner, Map clientMap, LinkedList clientNoList){

        try {
            System.out.println("--------------------------------------------\n" +
                    "Welcome to VALHALLA BANK.\n" +
                    "Please Enter Client No to proceed:");

            String userClientNo = scanner.next();
            boolean inputValidation = false;

            if (userClientNo.equals("-1")){
                atmOn = false;
                System.out.println("-1 entered, ATM is shutting down.");
                return null;
            }

            for (Object currClientNo : clientNoList) {

                if (userClientNo.equals(currClientNo)) {
                    inputValidation = true;
                    break;
                }
                else {
                    continue;
                }
            }

            if (inputValidation) {
                Client user = (Client) clientMap.get(userClientNo);
                System.out.println("\n--------------------------------------------\n" +
                        "Welcome Dear " + user.getName() +
                        "\n--------------------------------------------");

                System.out.println("Client INFOs :");
                System.out.println("Name Surname : " + user.getName());
                System.out.println("ID Number : " + user.getIdNumber());
                System.out.println("Account Balance : " + user.getAccountBalance());

                if (user instanceof CorporateClient) {
                    System.out.println("Company Name : " + ((CorporateClient) user).getCompanyName());
                }
                else if (user instanceof IndividualClient) {
                    System.out.println("Address : " + ((IndividualClient) user).getAddress());
                }
                else {
                    System.out.println("This is just a Client");
                }

                return user;
            }
            else {
                System.out.println("CLIENT_NO_OUT_OF_RANGE");
            }
        }

        catch (InputMismatchException e){
            System.out.println("INVALID_CLIENT_NO::ERROR::" + e);
        }
        catch (Exception e){
            System.out.println("ERROR::" + e);
        }

        return null;
    }

    public static void moneyOperationsEntry(Scanner scanner, MoneyOperationsAtm moneyOperationsAtm, Client user){

        try {
            System.out.println("Enter " + WITHDRAW_MONEY + "-(Withdraw) or " + DEPOSIT_MONEY + "-(Deposit)");
            String operationKey = scanner.next();

            if (operationKey.equals(WITHDRAW_MONEY)){
                System.out.println("Enter quantity of money to withdraw : ");
                double withdrawQuantity = scanner.nextFloat();
                user.setAccountBalance(moneyOperationsAtm.withdrawMoney(user.getAccountBalance(), withdrawQuantity));
                System.out.println("Current Account Balance of"+ user.getName() +" is " + user.getAccountBalance());
            }
            else if (operationKey.equals(DEPOSIT_MONEY)){
                System.out.println("Enter quantity of money to deposit : ");
                double depositQuantity = scanner.nextFloat();
                user.setAccountBalance(moneyOperationsAtm.depositMoney(user.getAccountBalance(), depositQuantity));
                System.out.println("Current Account Balance of "+ user.getName() +" is " + user.getAccountBalance());
            }
            else{
                throw new InputMismatchException();
            }
        }
        catch (InputMismatchException e){

            System.out.println("INVALID_INPUT::ERROR::" + e);
        }
        catch (NullPointerException e){

            System.out.println("CLIENT_COULD_NOT_FOUND::ERROR::" + e);
        }

    }
}
