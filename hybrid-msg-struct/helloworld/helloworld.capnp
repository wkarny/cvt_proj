@0x9eb32e19f86ee174;
using Java = import "/capnp/java.capnp";
$Java.package("org.capnproto.examples");
$Java.outerClassname("KeyValues");

struct HelloRequest {
  name @0 :Text;
}

struct HelloResponce {
	reply @0 :Text;
}