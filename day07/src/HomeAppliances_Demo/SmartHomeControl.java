package HomeAppliances_Demo;

public class SmartHomeControl {

    //这一步是为了私有化构造方法（禁止外部new对象）
    private SmartHomeControl(){}

    //定义一个静态常量，保存唯一实例
    private static final SmartHomeControl INSTANCE=new SmartHomeControl();

    public static SmartHomeControl getInstance(){
        return INSTANCE;
    }

    public void control(JD device){//控制单个设备的开关
        device.press();
        System.out.println("【操作成功】"+device.getName()+"已切换至"+(device.isStatus()?"开启":"关闭")+"状态");
    }

    public void printAll(JD[] devices){
        System.out.println("====当前智能家居设备的状态====");
        for(int i = 0; i < devices.length; i++){
            JD device=devices[i];
            System.out.println((i+1)+"."+device.getName()+"："+(device.isStatus()?"已开启":"已关闭"));
        }
    }
}
