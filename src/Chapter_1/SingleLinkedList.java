package Chapter_1;

public class SingleLinkedList<T> {
    public Node<T> head = null;
    
    public class Node<T> {
        T data;
        Node<T> next = null; //������ ���� 
        
        public Node(T data) {
            this.data = data;
        }
    }
    
    public void addNode(T data) {
        if (head == null) {
            head = new Node<T>(data);
        } else {
            Node<T> node = this.head;
            while (node.next != null) { //����� ������ ���� ����
                node = node.next;
            } 
            node.next = new Node<T>(data); //����� �����Ͱ� �ǳ����� �������ϱ� �߰� ����  -> ���� 
        }
    }
    
    public void printAll() {
        if (head != null) {
            Node<T> node = this.head;
            System.out.println(node.data);
            while (node.next != null) {
                node = node.next;
                System.out.println(node.data);
            }
        }
    }
    
    public Node<T> search(T data) {
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.head;
            while (node != null) { 
                if (node.data == data) {
                    return node;
                } else {
                    node = node.next;
                }
            }
            return null;
        }
    }
    
    public void addNodeInside(T data, T isData) { //��� �����Ϳ� ������ ��ġ�� �ʿ��� �Ű����� - �׾տ� ���� ����� �������� ��
        Node<T> searchedNode = this.search(isData); //��ġ ������ ��� 
        
        if (searchedNode == null) { //ã������ �����Ͱ� ������ �ǵڿ� ����
            this.addNode(data);
        } else {
            Node<T> nextNode = searchedNode.next;
            searchedNode.next = new Node<T>(data); //������ ����
            searchedNode.next.next = nextNode;  // (searcheNode.next) => ��� ,
        }									    // (searcheNode.next.next) => ������ ó�� 
    }
    
    public boolean delNode(T isData) {
        if (this.head == null) {
            return false;
        } else {
            Node<T> node = this.head;
            if (node.data == isData) { //�����Ϸ��� �����Ͱ� ����� ���
                this.head = this.head.next; //��带 �Ű���
                return true;
            } else { //�����Ϸ��� �����Ͱ� ��尡 �ƴҰ��, �����Ϸ��� ������ �տ� ��尡 �������
                while (node.next != null) {
                    if (node.next.data == isData) {
                        node.next = node.next.next; //����� ������ ����� �ٴ��������ͷ� �ٲٰٴ�
                        return true;
                    }
                    node = node.next; //����
                }
                return false;
            }
        }
    }
    public static void main(String []args) {
    	
    	SingleLinkedList<Integer> MyLinkedList = new SingleLinkedList<Integer>();
    	MyLinkedList.addNode(1);
    	MyLinkedList.addNode(2);
    	MyLinkedList.addNode(3);

    	MyLinkedList.printAll();
    	System.out.println("---------------------");
    	
    	MyLinkedList.addNodeInside(5, 1);
    	MyLinkedList.printAll();
    	
    	System.out.println("---------------------");
    	MyLinkedList.addNodeInside(6, 3);
    	MyLinkedList.printAll();
    	
    	System.out.println("---------------------");
    	MyLinkedList.addNodeInside(7, 20);
    	MyLinkedList.printAll();
    	
    	System.out.println("---------------------");
    	MyLinkedList.delNode(3);
    	MyLinkedList.printAll();
    	
    	System.out.println("---------------------");
    	MyLinkedList.delNode(1);
    	MyLinkedList.printAll();
    	
    	System.out.println("---------------------");
    	MyLinkedList.delNode(20);
    	
    }
    
}

