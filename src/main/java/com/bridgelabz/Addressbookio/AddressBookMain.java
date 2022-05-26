package com.bridgelabz.Addressbookio;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain
{

    public static List<Contact> contactList = new ArrayList<>();

    public static Scanner scan = new Scanner(System.in);

    public static String firstname, lastname, address, email, city, state;
    public static int zip;
    public static long phone_number;

    public static AddressBookMain function = new AddressBookMain();

    public static void main(String[] args) {


        char ans;

        do {
            System.out.println("\n\n Here are some option for addressbook");
            System.out.println("1.Add the contact...");
            System.out.println("2.Edit the contact...");
            System.out.println("3.Delete the contact...");
            System.out.println("4.Display the contact in sorted order....");
            System.out.println("5.search by city or state and print...");
            System.out.println("6.search and print count of city and state...");
            System.out.println("7.view by sorted state or city or zip the contact...");


            System.out.print("\n\n Enter the choice What you want to do: ");
            int choice = scan.nextInt();


            switch (choice) {
                case 1: {
                    System.out.print("\nhow many no of contact to be added:");
                    int no_of_contact = scan.nextInt();
                    for (int i = 0; i < no_of_contact; i++) {
                        add_contact_to_list(i);
                        System.out.println("\nContact added Successfully....");
                    }
                }
                break;

                case 2: {
                    contactList.stream().forEach(name -> {
                        System.out.println("\n" + name.getFirstname());
                    });
                    System.out.print("\nwhich contact you want to edit:");
                    String edit_name = scan.next();
                    edit_contact_to_list(edit_name);
                    System.out.println("\nContact edited Successfully....");
                }
                break;

                case 3: {
                    contactList.stream().forEach(name -> {
                        System.out.println("\n" + name.getFirstname());
                    });
                    System.out.print("\nwhich contact you want to edit:");
                    String delete_name = scan.next();
                    delete_contact(delete_name);
                    System.out.println("\nContact deleted Successfully....");

                }
                break;

                case 4: {
                    display_all_contact();
                }
                break;

                case 5: {
                    System.out.print("\nEnter city or state to find persons:");
                    String search_person = scan.next();
                    search_by_city_or_state_get_multiple_person(search_person);
                }
                break;

                case 6: {
                    search_by_city_or_state_get_multiple_person_count();
                }
                break;

                case 7: {
                    view_by_state_or_city_sorted();
                }
                break;
                case 8: {
                    File_read_and_write();
                }
                break;
            }

            System.out.println("\n Do you want to continue:(y or n) ");
            ans = scan.next().charAt(0);
        } while (ans == 'y');
    }

    private static void File_read_and_write() {
        AddressBookFile.createFile();
        String input = contactList.toString();
        AddressBookFile.add_details_to_file(input);
        AddressBookFile.read_details_to_file();

    }

    private static void view_by_state_or_city_sorted() {
        System.out.println("\nBy which you what to search city or state or zip..");
        String type = scan.next();
        switch (type) {
            case "state": {
                List<Contact> sorted_by_state = contactList.stream()
                        .sorted(Comparator.comparing(Contact::getState))
                        .collect(Collectors.toList());
                sorted_by_state.forEach(contact -> {
                    System.out.println("\nFirstName - " + contact.getFirstname() +
                            "\nLastname -  " + contact.getLastname() +
                            "\nAddress -  " + contact.getAddress() +
                            "\nCity -  " + contact.getCity() +
                            "\nState -  " + contact.getState() +
                            "\nZip -  " + contact.getZip() +
                            "\nEmail -  " + contact.getEmail());
                });
            }
            break;

            case "city": {
                List<Contact> sorted_by_state = contactList.stream()
                        .sorted(Comparator.comparing(Contact::getCity))
                        .collect(Collectors.toList());
                sorted_by_state.forEach(contact -> {
                    System.out.println("\nFirstName - " + contact.getFirstname() +
                            "\nLastname -  " + contact.getLastname() +
                            "\nAddress -  " + contact.getAddress() +
                            "\nCity -  " + contact.getCity() +
                            "\nState -  " + contact.getState() +
                            "\nZip -  " + contact.getZip() +
                            "\nEmail -  " + contact.getEmail());
                });
            }
            break;

            case "zip": {
                List<Contact> sorted_by_state = contactList.stream()
                        .sorted(Comparator.comparing(Contact::getZip))
                        .collect(Collectors.toList());
                sorted_by_state.forEach(contact -> {
                    System.out.println("\nFirstName - " + contact.getFirstname() +
                            "\nLastname -  " + contact.getLastname() +
                            "\nAddress -  " + contact.getAddress() +
                            "\nCity -  " + contact.getCity() +
                            "\nState -  " + contact.getState() +
                            "\nZip -  " + contact.getZip() +
                            "\nEmail -  " + contact.getEmail());
                });
            }
            break;
        }
    }


    private static void search_by_city_or_state_get_multiple_person_count() {
        Map<String, Integer> city = contactList.parallelStream()
                .collect(Collectors.toConcurrentMap(Contact::getCity, c -> 1, Integer::sum));
        Map<String, Integer> state = contactList.parallelStream()
                .collect(Collectors.toConcurrentMap(Contact::getState, s -> 1, Integer::sum));
        System.out.println("City Name" + city.keySet() + ":  Number of persons in City " + city.values()
                + "        State Name" + state.keySet() + ":  Number of persons in State " + state.values());

    }


    private static void search_by_city_or_state_get_multiple_person(String input) {
        if (contactList.stream().anyMatch(search -> search.getState().equalsIgnoreCase(input) || search.getCity().equalsIgnoreCase(input))) {
            contactList.stream().filter(search -> search.getState().equalsIgnoreCase(input) || search.getCity().equalsIgnoreCase(input))
                    .sorted().forEach(contact -> {
                        System.out.println("Contact person: " + contact.getFirstname());
                    });
        } else {
            System.out.println("\nThere is no contact person");
        }
    }


    private static void delete_contact(String name) {
        int index = (contactList.indexOf(AddressBook.book.get(name))) + 1;
        AddressBook.book.remove(name);
        contactList.remove(index);
    }

    private static void display_all_contact() {

        TreeMap<String, Contact> sorted = new TreeMap<>();
        sorted.putAll(AddressBook.book);
        for (Map.Entry<String, Contact> entry : sorted.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
    }

    private static void add_contact_to_list(int index) {
        String first_name = function.get_firstname();
        boolean present = function.check_duplicate(first_name);
        while (present) {
            System.out.println("The name is already taken");
            first_name = function.get_firstname();
            present = function.check_duplicate(first_name);
        }
        firstname = first_name;
        System.out.print("\nEnter the Last name: ");
        lastname = scan.next();
        System.out.print("\nEnter the Address: ");
        address = scan.next();
        System.out.print("\nEnter the city: ");
        city = scan.next();
        System.out.print("\nEnter the state: ");
        state = scan.next();
        System.out.print("\nEnter the zip: ");
        zip = scan.nextInt();
        System.out.print("\nEnter the phone number: ");
        phone_number = scan.nextLong();
        System.out.print("\nEnter the EmailID: ");
        email = scan.next();

        contactList.add(new Contact(firstname, lastname, address, city, state, zip, phone_number, email));
        AddressBook.book.put(firstname, new Contact(firstname, lastname, address, city, state, zip, phone_number, email));
    }

    private String get_firstname() {
        System.out.print("\nEnter the First name: ");
        firstname = scan.next();
        return firstname;
    }

    private boolean check_duplicate(String first_name) {
        return contactList.stream().anyMatch(name -> name.getFirstname().equals(first_name));
    }

    private static void edit_contact_to_list(String name) {

        String first_name = function.get_firstname();
        boolean present = function.check_duplicate(first_name);
        while (present) {
            System.out.println("The name is already taken");
            first_name = function.get_firstname();
            present = function.check_duplicate(first_name);
        }
        firstname = first_name;
        System.out.print("\nEnter the Last name: ");
        lastname = scan.next();
        System.out.print("\nEnter the Address: ");
        address = scan.next();
        System.out.print("\nEnter the city: ");
        city = scan.next();
        System.out.print("\nEnter the state: ");
        state = scan.next();
        System.out.print("\nEnter the zip: ");
        zip = scan.nextInt();
        System.out.print("\nEnter the phone number: ");
        phone_number = scan.nextLong();
        System.out.print("\nEnter the EmailID: ");
        email = scan.next();

        int index = (contactList.indexOf(AddressBook.book.get(name))) + 1;
        AddressBook.book.remove(name);
        contactList.set(index, new Contact(firstname, lastname, address, city, state, zip, phone_number, email));
        AddressBook.book.put(firstname, new Contact(firstname, lastname, address, city, state, zip, phone_number, email));
    }
}