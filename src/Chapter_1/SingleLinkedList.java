package Chapter_1;

public class SingleLinkedList<T> {
    public Node<T> head = null;
    
    public class Node<T> {
        T data;
        Node<T> next = null; //포인터 개념 
        
        public Node(T data) {
            this.data = data;
        }
    }
    
    public void addNode(T data) {
        if (head == null) {
            head = new Node<T>(data);
        } else {
            Node<T> node = this.head;
            while (node.next != null) { //노드의 끝으로 가는 과정
                node = node.next;
            } 
            node.next = new Node<T>(data); //노드의 포인터가 맨끝으로 가있으니까 추가 가능  -> 연결 
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
    
    public void addNodeInside(T data, T isData) { //어느 데이터에 넣을지 위치가 필요한 매개변수 - 그앞에 놓인 노드의 데이터의 값
        Node<T> searchedNode = this.search(isData); //위치 데이터 노드 
        
        if (searchedNode == null) { //찾으려는 데이터가 없을때 맨뒤에 삽입
            this.addNode(data);
        } else {
            Node<T> nextNode = searchedNode.next;
            searchedNode.next = new Node<T>(data); //데이터 삽입
            searchedNode.next.next = nextNode;  // (searcheNode.next) => 노드 ,
        }									    // (searcheNode.next.next) => 포인터 처리 
    }
    
    public boolean delNode(T isData) {
        if (this.head == null) {
            return false;
        } else {
            Node<T> node = this.head;
            if (node.data == isData) { //삭제하려는 데이터가 헤드일 경우
                this.head = this.head.next; //헤드를 옮겨줌
                return true;
            } else { //삭제하려는 데이터가 헤드가 아닐경우, 삭제하려는 데이터 앞에 노드가 있을경우
                while (node.next != null) {
                    if (node.next.data == isData) {
                        node.next = node.next.next; //노드의 다음을 노드의 다다음데이터로 바꾸겟다
                        return true;
                    }
                    node = node.next; //적용
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

