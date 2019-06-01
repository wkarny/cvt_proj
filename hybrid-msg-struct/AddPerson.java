import com.grpc.hybrid.HybridAddressBookProtos.HybridAddressBook;
import com.grpc.hybrid.HybridAddressBookProtos.AddressBook;
import com.grpc.hybrid.HybridAddressBookProtos.Person;
import com.grpc.hybrid.HybridAddressBookProtos.CapnpWrapper; 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

import org.capnproto.examples.HybridAddressBookMain;
import org.capnproto.BufferedOutputStream;
import org.capnproto.ArrayOutputStream;
import java.util.Scanner;
import java.nio.ByteBuffer;
import com.google.protobuf.ByteString;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

//import java.io.FileOutputStream;
//import java.io.FileInputStream;
//import org.capnproto.BufferedOutputStream;
//import org.capnproto.ArrayOutputStream;
//import java.io.OutputStream;
//import java.io.ByteArrayOutputStream;
//import java.nio.ByteBuffer;
import java.io.FileDescriptor;
import java.io.File;

import org.capnproto.StructList;
import org.capnproto.examples.CAddressbook.CAddressBook;
import org.capnproto.examples.CAddressbook.CPerson;

class AddPerson {

  static Person PromptForAddress(BufferedReader stdin,
                                  PrintStream stdout) throws IOException {
    Person.Builder person = Person.newBuilder();

    person.setId(123);
    person.setName("Alice");
    person.setEmail("alice@example.com");

    Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder().setNumber("555-1212");   
    phoneNumber.setType(Person.PhoneType.MOBILE);
    person.addPhones(phoneNumber);

    return person.build();
  }

  static void GetCapnpMsg(OutputStream os) throws IOException {
    org.capnproto.MessageBuilder message = new org.capnproto.MessageBuilder();
    CAddressBook.Builder addressbook = message.initRoot(CAddressBook.factory);
    StructList.Builder<CPerson.Builder> people = addressbook.initPeople(2);

    CPerson.Builder alice = people.get(0);
    alice.setId(123);
    alice.setName("Alice");
    alice.setEmail("alice@example.com");

    StructList.Builder<CPerson.PhoneNumber.Builder> alicePhones = alice.initPhones(1);
    alicePhones.get(0).setNumber("555-1212");
    alicePhones.get(0).setType(CPerson.PhoneNumber.Type.MOBILE);

    org.capnproto.SerializePacked.writeToUnbuffered(Channels.newChannel(os),message);
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
      System.exit(-1);
    }

    

    HybridAddressBook.Builder hab = HybridAddressBook.newBuilder();

    Scanner scan = new Scanner(System.in);

    //System.out.println("Enter message type : 1. Protobuf, 2. Capnp :");
    int choice =  2;//scan.nextInt();

    if(choice == 1) {

      long t = System.currentTimeMillis();

      AddressBook.Builder addressBook = AddressBook.newBuilder();

      addressBook.addPeople(
        PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                         System.out));

      hab.setMsgType(1);
      hab.setAddressbookProto(addressBook.build());

      System.out.println("Time required to encode : " + (System.currentTimeMillis() - t));

      t = System.currentTimeMillis();

      FileOutputStream output = new FileOutputStream(args[0]);
      try {
        hab.build().writeTo(output);
      } finally {
        output.close();
      }

      System.out.println("Time required to write to file : " + (System.currentTimeMillis() - t));

    }
    else {

      //ByteBuffer buff = ByteBuffer.allocate(80);

      //BufferedOutputStream bs = new ArrayOutputStream(buff);

      ByteArrayOutputStream os = new ByteArrayOutputStream(); 

      long t = System.currentTimeMillis();

      hab.setMsgType(2);

      CapnpWrapper.Builder capnp_data = CapnpWrapper.newBuilder();

      GetCapnpMsg(os);
      

      //int i;
      //for(i = 0; i < bf.length ; i ++) {
      //  byte[] cnt = bf.array();
        capnp_data.setData(ByteString.copyFrom(os.toByteArray()));
      //}

      hab.setAddressbookCapnp(capnp_data.build());

      System.out.println("Time required to encode : " + (System.currentTimeMillis() - t));

      t = System.currentTimeMillis();
      

      FileOutputStream output = new FileOutputStream(args[0]);
      try {
        hab.build().writeTo(output);
      } finally {
        output.close();
      }

      System.out.println("Time required to write to file : " + (System.currentTimeMillis() - t));
     

    }

  }

}

