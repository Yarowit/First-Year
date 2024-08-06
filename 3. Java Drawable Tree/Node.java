public class Node <T extends Comparable<T>>{
    public T value;
    public Node<T> left = null, right = null; 

    Node(T val){
        value = val;
    }

    public void draw(){
        draw(this);
    }

    private void draw(Node<T> node){
        if(node == null)
            return;
        draw(node.left);
        System.out.println(node.value);
        draw(node.right);
    }
    
    public Boolean search(T val){
        Node<T> child = this;
        
        while(child != null){
            if(child.value == val)
                return true;
                
            if(val.compareTo(child.value) < 0)
                child = child.left;
            else
                child = child.right;
        }
        return false;
    }

    public void insert(T val){
        Node<T> child = this;
    
        while(true){
            if(val.compareTo(child.value) < 0)
                if(child.left != null)
                    child = child.left;
                else{
                    child.left = new Node<T>(val);
                    return;
                }
            else
                if(child.right != null)
                    child = child.right;
                else{
                    child.right = new Node<T>(val);
                    return;
                }
        }
    }

    private void recursiveInsert(Node<T> root, Node<T> node){
        if(node == null)
            return;

        root.insert(node.value);
        recursiveInsert(root, node.left);
        recursiveInsert(root, node.right);
    }

    public void delete(T val){
        Node<T> parent = this;
        
        if(parent.value != val) while(parent != null){
            if(parent.left != null && parent.left.value == val){
                if(parent.left.right == null && parent.left.left == null){
                    parent.left = null;
                    return;
                }
    
                parent = parent.left;
                break;
            }

            if(parent.right != null && parent.right.value == val){
                if(parent.right.right == null && parent.right.left == null){
                    parent.right = null;
                    return;
                }

                parent = parent.right;
                break;
            }

            if(val.compareTo(parent.value) > 0){
                parent = parent.right;
            }else{
                parent = parent.left;
            }
        }

        if(parent == null)
            return;


        Node<T> one, two, three;

        if(parent.left != null){
            parent.value = parent.left.value;
            one = parent.left.left;
            two = parent.left.right;
            three = parent.right;
        }else{
            parent.value = parent.right.value;
            one = parent.right.right;
            two = parent.right.left;
            three = parent.left;
        }


        parent.left = null;
        parent.right = null;
        recursiveInsert(parent, one);
        recursiveInsert(parent, two);
        recursiveInsert(parent, three);

    }

    // public void print(String ac){
    //     System.out.println(ac + value);
    //     if(left != null)
    //         left.print(ac + " ");
    //     if(right != null)
    //         right.print(ac + " ");
    // }
    
    public int measure(int size){
        return measure(this,size);
    }

    private int measure(Node<T> node, int size){
        if(node == null)
            return size;
        
        int a = measure(node.left, size + 1);
        int b = measure(node.right, size + 1);

        if(a<b)
            return b;
        else
            return a;
    }
}
