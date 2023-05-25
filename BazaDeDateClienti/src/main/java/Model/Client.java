
/** Initializes a new instance of the Client class with the specified values for id, name, address, email, and age.
 *
 * @param id      The client ID.
 * @param name    The client name.
 * @param address The client address.
 * @param email   The client email.
 * @param age     The client age.
 * Gets the client ID.
 *
 * @return The client ID.
 * Sets the client ID.
 *
 * @param id The client ID to set.
 * Gets the client name.
 *
 * @return The client name.
 * Sets the client name.
 *
 * @param name The client name to set.
 * Gets the client address.
 *
 * @return The client address.
 * Sets the client address.
 *
 * @param address The client address to set.
 * Gets the client email.
 *
 * @return The client email.
 */
package Model;

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private int age;
    public Client()
    {

    }
    public Client(int id, String name, String address, String email, int age) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", age=" + age
                + "]";
    }

}

