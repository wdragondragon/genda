package QQ;

import java.awt.List;
import java.util.ArrayList;

import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.HWND;
import org.xvolks.jnative.util.User32;

public class GetQQWindow {
  public ArrayList<String> name = new ArrayList<String>();

  GetQQWindow() {
    final ArrayList<HWND> hlist = new ArrayList<HWND>();
    GetAllWindow d = new GetAllWindow();
    d.demo();
    for (int i = 0; i < d.name.size(); i++) {
      System.out.println(d.name.get(i));
      try {
        HWND hWnd = User32.FindWindow("TXGuiFoundation", d.name.get(i));
        if (hWnd.getValue() > 0) {
          hlist.add(hWnd);
          name.add(d.name.get(i));
        }
      } catch (IllegalAccessException | NativeException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
