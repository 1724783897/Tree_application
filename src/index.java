import java.util.Scanner;

public class index {
    public static void main(String args[]){
        TreeNode t1 = new TreeNode();
        t1.createTree1("C:\\Users\\Lenovo\\Desktop\\area2016.txt");
        //创建一个新的空结点用于接受数据
        Scanner scan = new Scanner(System.in);

        Status r;
        int pid;
        int cid;
        String cname;
        int ch = -1;
        while(ch != 0){
            System.out.println("---------------");
            System.out.println("1 - add");
            System.out.println("2 - find");
            System.out.println("3 - proOrder");
            System.out.println("4 - height");
            System.out.println("5 - width");
            System.out.println("6 - setName");
            System.out.println("7 - delete");
            System.out.println("0 - exit");
            System.out.println("---------------");
            System.out.print("select:");
            ch = scan.nextInt();

            switch(ch){
                case 1:
                    //add
                    System.out.print("enter parent's value:");
                    pid = scan.nextInt();
                    System.out.print("enter child's value:");
                    cid = scan.nextInt();
                    cname= scan.next();
                    r = t1.add(pid,cid,cname);
                    switch(r){
                        case OK:
                            System.out.println("OK");
                            break;
                        case EXIST:
                            System.out.println("Error : EXIST");
                            break;

                        case PARENT_NOT_EXIST:
                            System.out.println("Error : PARENT_NOT_EXIST");
                            break;
                    }
                    break;

                case 2:
                    System.out.print("enter value to find:");
                    int findValueById = scan.nextInt();
                    TreeNode f_node =t1.find(findValueById);

                    if(f_node!=null)
                        System.out.println("城市为："+f_node.name);
                    else
                        System.out.println("请输入正确的id");
                    break;

                case 3:
                    if(t1.first!= null)
                        t1.preOrder();
                    break;

                case 4:
                    int k=t1.height();
                    System.out.print("树的高度是");
                    System.out.println(k);
                    break;
                case 5:
                    System.out.println("enter parent id:");
                    int tree_value = scan.nextInt();
                    int hMax=t1.width(tree_value);
                    System.out.println("树的度是:"+hMax);
                    break;
                case 6:
                    System.out.println("enter current setName's id:");
                    int setId = scan.nextInt();
                    System.out.println("enter current setName's newName:");
                    String newName = scan.next();
                    r = t1.setName(setId,newName);
                    if (r == Status.OK){
                        System.out.println("change successfully");
                    }
                case 7:
                    System.out.println("enter delete id :");
                    int deleteId = scan.nextInt();
                    r = t1.deleteTree(deleteId);
                    if (r == Status.OK){
                        System.out.println("change successfully");
                    }

            }

        }
        scan.close();
    }
}
