package ui.center;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 20/10/13
 * Time: 10:13
 */

public class NumberField extends TextField {

    public NumberField() {
        super();
        setAlignment(Pos.BASELINE_RIGHT);
    }

    @Override public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]*") || text.matches("[.]*") || (text.matches("[-]*") ) ) {
            super.replaceText(start, end, text);
        }
    }

    @Override public void replaceSelection(String text) {
        if (text.matches("[0-9]*") || text.matches("[.]*") || (text.matches("[-]*") ) ) {
            super.replaceSelection(text);
        }
    }
}
