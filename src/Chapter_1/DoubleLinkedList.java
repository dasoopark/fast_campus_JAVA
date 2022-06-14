package Chapter_1;

public class DoubleLinkedList<T> {
    public Node<T> head = null; 
    public Node<T> tail = null;

    public class Node<T> {
        T data;
        Node<T> prev = null; // <-쪽 노드를 가르키는 포인터
        Node<T> next = null; // ->쪽 노드를 가르키는 포인터

        public Node(T data) {
            this.data = data;
        }
    }

    public void addNode(T data) {
        if (this.head == null) {
            this.head = new Node<T>(data);
            this.tail = this.head;
        } else {
            Node<T> node = this.head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<T>(data);
            node.next.prev = node; //노드의 다음 이전의 포인터 위치는 노드를 가르킴
            this.tail = node.next;
        }
    }

    public void printAll() {
        if (this.head != null) {
            Node<T> node = this.head;
            System.out.println(node.data);
            while (node.next != null) {
                node = node.next;
                System.out.println(node.data);
            }
        }
    }

    public T searchFromHead(T isData) { //헤드에서	특정 노드 찾는 메소드 
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.head; //노드에 헤드를 삽입 
            while (node != null) {
                if (node.data == isData) {
                    return node.data;
                } else {
                    node = node.next;
                }
            }
            return null;
        }
    }

    public T searchFromTail(T isData) { //꼬리에서 특정 노드 찾는 메소드 
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.tail; 
            while (node != null) {
                if (node.data == isData) {
                    return node.data;
                } else {
                    node = node.prev; // 뒤로 이동 (tail 이니깐)
                }
            }
            return null;
        }
    }

    // 異붽�
    public boolean insertToFront(T existedData, T addData) { //노드를 앞에 <- 삽입하는 메소드 
        if (this.head == null) {
            this.head = new Node<T>(addData); //데이터 없을때 헤더로 설정 
            this.tail = this.head; //tail도 헤더로 설정해줘야함 
            return true;
        } else if (this.head.data == existedData) { //새로운 헤더를 삽입할 경우
            Node<T> newHead = new Node<T>(addData);
            newHead.next = this.head;
            this.head = newHead;
            this.head.next.prev=this.head; //
            return true; 
        } else {
            Node<T> node = this.head;  //그 이후에 연결된 노드 중에 하나 일 경우 
            while (node != null) {
                if (node.data == existedData) { 
                    // nodePrev -> node -> nodeNext
                    Node<T> nodePrev = node.prev; //기존에 존재하던 앞에 노드 연결  |(이거) <-|
                    // nodePrev -> new node -> node -> nodeNext                              (nodePrev)      (node)
                    nodePrev.next = new Node<T>(addData);  //기존에 존재하던 앞에노드 다음에 add될 데이터 삽입이 됨 |    (추가)    |
                    nodePrev.next.next = node;  //데이터가 추가 된 노드의 네스트는 그다음 노드를 가리킴
                    nodePrev.next.prev = nodePrev; //새로생성된 노드의 prev는 앞에 밀린 노드를 가리켜야함 
                    node.prev = nodePrev.next; //기존 노드의 prev에 새로 추가된 노드를 연결시켜줘야 함 
                    
                    return true; 
                } else {
                    node = node.next;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> MyLinkedList = new DoubleLinkedList<Integer>();

        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);
        MyLinkedList.addNode(4);
        MyLinkedList.addNode(5);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.insertToFront(3, 2);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.insertToFront(6, 2);
        MyLinkedList.insertToFront(1, 00);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.addNode(6);
        MyLinkedList.printAll();

    }
}