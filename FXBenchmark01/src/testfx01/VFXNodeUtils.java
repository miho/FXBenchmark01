/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx01;

import javafx.scene.Group;
import javafx.scene.Node;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class VFXNodeUtils {
    // no instanciation allowed

    private VFXNodeUtils() {
        throw new AssertionError(); // not in this class either!
    }

    /**
     * Returns the global scale of the specified node.
     *
     * @param n node
     * @return the global scale of the specified node
     */
    public static Scale getGlobalScale(Node n) {
        return _getGlobalScale(n,
                new Scale(n.getScaleX(), n.getScaleY(), n.getScaleZ()));
    }

    /**
     * Returns the global scale of the ancestors of the specified node.
     *
     * @param n node
     * @return the global scale of the ancestors of the specified node
     */
    public static Scale getGlobalParentScale(Node n) {
        return _getGlobalScale(n,
                new Scale(1, 1, 1));
    }

    private static Scale _getGlobalScale(Node n, Scale scale) {
        Node parent = n.getParent();

        if (parent != null) {
            scale = new Scale(
                    parent.getScaleX() * scale.getScaleX(),
                    parent.getScaleY() * scale.getScaleY(),
                    parent.getScaleZ() * scale.getScaleZ());

            scale = _getGlobalScale(parent, scale);
        }

        return scale;
    }
    
    public static void removeFromParent(Node n) {
        if (n.getParent() instanceof Group) {
            ((Group)n.getParent()).getChildren().remove(n);
        } else if (n.getParent() instanceof ZoomableNode) {
            ((ZoomableNode)n.getParent()).removeNode(n);
        }
    }
}
