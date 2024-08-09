public class Tree <T extends Comparable<T>>{
    private class Node <t extends Comparable<T>>{
        private t value;
        private Node<t> parent, left = null, right = null;

        public String inOrderGenerator(){
            String l = "", r = "";
            if(this.left != null)
                l = this.left.inOrderGenerator();
            if(this.right != null)
                r = this.right.inOrderGenerator();
            return l + " : " + this.value + " : " + r;
        }

        public String draw(String spacing){
            String s = "";
            if(this.right != null)
                s += this.right.draw(spacing + "      ");

            s += spacing + this.value + " --{\n";

            if(this.left != null)
                s += this.left.draw( spacing + "      ");
                
            return s;
        }

        Node(t value, Node<t> parent){
            this.value = value;
            this.parent = parent;
        }
    }

    Node<T> root;

    void insert(T value){
        if(this.root == null){
            this.root = new Node<T>(value,null);
            return;
        }

        Node<T> node = this.root;

        while(true){
            if(value.compareTo(node.value) < 0){
                if(node.left != null){
                    node = node.left;
                }else{
                    node.left = new Node<T>(value, node);
                    node.left.parent = node;
                    return;
                }
            }else{
                if(node.right != null){
                    node = node.right;
                }else{
                    node.right = new Node<T>(value, node);
                    return;
                }
            }
        }
    }

    public Boolean search(T val){
        if(this.root == null)
            return false;

        Node<T> node = this.root;
        
        while(node != null){
            if(node.value.equals(val))
                return true;
                
            if(val.compareTo(node.value) < 0)
                node = node.left;
            else
                node = node.right;
        }
        return false;
    }

    public String inOrder(){
        if(this.root == null)
            return "--";
        return root.inOrderGenerator();
    }

    public String draw(){
        if(this.root == null)
            return "--";
        return this.root.draw("");
    }

    public void delete(T value){
        Node<T> node = this.root;
        if(node == null)
            return;
    
        // Znajdowanie node'a
        while(node.value.equals(value) == false){                
            if(value.compareTo(node.value) < 0)
                node = node.left;
            else
                node = node.right;
    
            if(node == null)
                return;
        }

        if(node.parent == null)
            if(this.root.left == null){
                if(this.root.right == null){
                    this.root = null;
                    return;
                }
                this.root = this.root.right;
                this.root.parent = null;
                return;
            }else
                this.root = this.root.left;
        else if(value.compareTo(node.parent.value) < 0){
            if(node.left == null){
                node.parent.left = node.right;
                if(node.right != null)
                    node.right.parent = node.parent;
                return;
            }
            node.parent.left = node.left;
        }else{
            if(node.left == null){
                node.parent.right = node.right;
                if(node.right != null)
                    node.right.parent = node.parent;
                return;
            }
            node.parent.right = node.left;
        }
    
        node.left.parent = node.parent;
        
        Node<T> rightRoot = node.right;
    
        node = node.left;

        while(node.right!= null)
            node = node.right;
        
        node.right = rightRoot;
        if(rightRoot != null)
            rightRoot.parent = node;
    }
}
