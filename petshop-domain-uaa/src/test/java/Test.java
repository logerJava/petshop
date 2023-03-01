import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author loger
 * @date 2023/2/28 15:06
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }
}
