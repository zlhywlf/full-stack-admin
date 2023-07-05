package zlhywlf.javaagent.filter;

import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import static org.objectweb.asm.Opcodes.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.function.Function;

public class InetAddressFilter extends Filter {

    public InetAddressFilter() {
        super("java/net/InetAddress");
    }

    @Override
    public void accept(MethodNode arg0) {
        if ("getAllByName".equals(arg0.name)
                && "(Ljava/lang/String;Ljava/net/InetAddress;)[Ljava/net/InetAddress;".equals(arg0.desc)) {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(ALOAD, 0));
            list.add(new MethodInsnNode(INVOKESTATIC, "zlhywlf/javaagent/filter/InetAddressFilter", "doFilter01",
                    "(Ljava/lang/String;)V", false));
            arg0.instructions.insert(list);
            return;
        }

        if ("isReachable".equals(arg0.name) && "(Ljava/net/NetworkInterface;II)Z".equals(arg0.desc)) {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(ALOAD, 0));
            list.add(new MethodInsnNode(INVOKESTATIC, "zlhywlf/javaagent/filter/InetAddressFilter", "doFilter02",
                    "(Ljava/net/InetAddress;)Ljava/lang/Object;", false));
            list.add(new VarInsnNode(ASTORE, 4));
            list.add(new InsnNode(ACONST_NULL));
            list.add(new VarInsnNode(ALOAD, 4));
            LabelNode label = new LabelNode();
            list.add(new JumpInsnNode(IF_ACMPEQ, label));
            list.add(new InsnNode(ICONST_0));
            list.add(new InsnNode(IRETURN));
            list.add(label);
            arg0.instructions.insert(list);
        }
    }

    static Function<String, Boolean> rule = host -> host.endsWith("account.jetbrains.com")
            || host.endsWith("18.200.1.33")
            || host.endsWith("63.32.213.158");

    public static void doFilter01(String host) throws IOException {
        if (null == host) {
            return;
        }
        System.out.println("host: " + host);
        if (rule.apply(host)) {
            System.out.println("stop: " + host);
            throw new java.net.UnknownHostException();
        }
    }

    public static Object doFilter02(InetAddress n) {
        if (null == n) {
            return null;
        }
        String host = n.getHostName();
        if (rule.apply(host)) {
            return false;
        }
        return null;
    }

}
