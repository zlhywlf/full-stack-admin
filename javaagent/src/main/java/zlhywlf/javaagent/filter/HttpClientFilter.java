package zlhywlf.javaagent.filter;

import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import static org.objectweb.asm.Opcodes.*;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HttpClientFilter extends Filter {

    public HttpClientFilter() {
        super("sun/net/www/http/HttpClient");
    }

    @Override
    public void accept(MethodNode arg0) {
        if ("openServer".equals(arg0.name) && "()V".equals(arg0.desc)) {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(ALOAD, 0));
            list.add(new FieldInsnNode(GETFIELD, "sun/net/www/http/HttpClient", "url", "Ljava/net/URL;"));
            list.add(new MethodInsnNode(INVOKESTATIC, "zlhywlf/javaagent/filter/HttpClientFilter", "doFilter",
                    "(Ljava/net/URL;)V", false));
            arg0.instructions.insert(list);
        }
    }

    public static void doFilter(URL url) throws IOException {
        if (null == url) {
            return;
        }
        String str = url.toString();
        if ("https://account.jetbrains.com/lservice/rpc/validateKey.action".equals(str)) {
            throw new SocketTimeoutException("connect timed out");
        }
    }

}
