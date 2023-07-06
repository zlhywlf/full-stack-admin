package zlhywlf.javaagent.filter;

import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import static org.objectweb.asm.Opcodes.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BigIntegerFilter extends Filter {

    public BigIntegerFilter() {
        super("java/math/BigInteger");
    }

    @Override
    public void accept(MethodNode arg0) {
        if ("oddModPow".equals(arg0.name)
                && "(Ljava/math/BigInteger;Ljava/math/BigInteger;)Ljava/math/BigInteger;".equals(arg0.desc)) {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(ALOAD, 0));
            list.add(new VarInsnNode(ALOAD, 1));
            list.add(new VarInsnNode(ALOAD, 2));
            list.add(new MethodInsnNode(INVOKESTATIC, "zlhywlf/javaagent/filter/BigIntegerFilter", "doFilter1",
                    "(Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;)Ljava/math/BigInteger;",
                    false));
            list.add(new VarInsnNode(ASTORE, 3));
            list.add(new InsnNode(ACONST_NULL));
            list.add(new VarInsnNode(ALOAD, 3));
            LabelNode label1 = new LabelNode();
            list.add(new JumpInsnNode(IF_ACMPEQ, label1));
            list.add(new VarInsnNode(ALOAD, 3));
            list.add(new InsnNode(ARETURN));
            list.add(label1);

            arg0.instructions.insert(list);
        }
    }

    private static final String KEY1 = "132539275706470611959025442358596065339277183129371451515942746839941395290940029288911295283395971613218548753609401534391051918648743955341431891652612867833224787211305352426166038477490462621771505344848529103250985514902037370656235856818944401159661608836512316624247437140966816080340361607990879955622213322630406657456243651521827230859560442236348107749359231436899813033357980882241101305017417986301928055413660816703882961240301133003040633547611604905731216000425099816190501098643541635507800151377165433036995797794978477378099217815876095872881079009945368246595405717506453177117263463608421965358111176539431118459411543794633413564213212914032108833628040232679914698147528818788410213583496405839128991889794512727302299304746683929173606145919770659253961781398579591162420014547033807832539948846722979743686995919787517644518813326191681049805199745284964914958173875065453735104610340827702355176992138604071958721937660544238021527246612988936041819282623968086226118487727747987374995520278858080152921560903475300092869240317914144892168813301340878740091982015570529023858694626692582834247458414753808285144557449740480292685215168828936724293257025511450313062283402805050999385256342017812922511100321,65537,860106576952879101192782278876319243486072481962999610484027161162448933268423045647258145695082284265933019120714643752088997312766689988016808929265129401027490891810902278465065056686129972085119605237470899952751915070244375173428976413406363879128531449407795115913715863867259163957682164040613505040314747660800424242248055421184038777878268502955477482203711835548014501087778959157112423823275878824729132393281517778742463067583320091009916141454657614089600126948087954465055321987012989937065785013284988096504657892738536613208311013047138019418152103262155848541574327484510025594166239784429845180875774012229784878903603491426732347994359380330103328705981064044872334790365894924494923595382470094461546336020961505275530597716457288511366082299255537762891238136381924520749228412559219346777184174219999640906007205260040707839706131662149325151230558316068068139406816080119906833578907759960298749494098180107991752250725928647349597506532778539709852254478061194098069801549845163358315116260915270480057699929968468068015735162890213859113563672040630687357054902747438421559817252127187138838514773245413540030800888215961904267348727206110582505606182944023582459006406137831940959195566364811905585377246353";
    private static final String VALUE1 = "31872219281407242025505148642475109331663948030010491344733687844358944945421064967310388547820970408352359213697487269225694990179009814674781374751323403257628081559561462351695605167675284372388551941279783515209238245831229026662363729380633136520288327292047232179909791526492877475417113579821717193807584807644097527647305469671333646868883650312280989663788656507661713409911267085806708237966730821529702498972114194166091819277582149433578383639532136271637219758962252614390071122773223025154710411681628917523557526099053858210363406122853294409830276270946292893988830514538950951686480580886602618927728470029090747400687617046511462665469446846624685614084264191213318074804549715573780408305977947238915527798680393538207482620648181504876534152430149355791756374642327623133843473947861771150672096834149014464956451480803326284417202116346454345929350148770746553056995922154382822307758515805142704373984019252210715650875853634697920708113806880196144197384637328982263167395073688501517286678083973976140696077590122053014085412828620051470085033364773099146103525313018873319293728800442101520384088109603555959893639842091339193931792571941325459569230969325716520466114654650519763224392421954596283135003";

    private static final Map<String, BigInteger> cached01 = new ConcurrentHashMap<>();
    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put(KEY1, VALUE1);
    }

    public static BigInteger doFilter1(BigInteger x, BigInteger y, BigInteger z) {
        String key = String.format("%s,%s,%s", x, y, z);
        if (map.containsKey(key)) {
            System.out.println(key);
            return cached01.computeIfAbsent(key, k -> new BigInteger(map.get(key)));
        }
        return null;
    }

}
