package Chapter_1;

import java.util.ArrayList;
import java.util.Collections;

public class Heap {
    // 보통 내부 attribute 는 private 으로 선언하기도 하지만, 외부에서도 간단히 데이터를 확인할 수 있도록 public 으로 선언
    public ArrayList<Integer> heapArray = null;
    

    public Heap(Integer data) {
        this.heapArray = new ArrayList<Integer>();
        // 배열은 인덱스가 0번부터 시작하지만, 힙 구현의 편의를 위해, root 노드 인덱스 번호를 1로 지정하기 위해,
        // 0번 인덱스에는 강제로 null 을 넣어주기로 함
        this.heapArray.add(null);
        this.heapArray.add(data);
    }

    public boolean move_up(Integer inserted_idx) { //바꿔줘야 되는걸 판단하는 메서드
        if (inserted_idx <= 1) { //1이면 루트노드를 의미.
            return false;
        }
        Integer parent_idx = inserted_idx / 2;
        if (this.heapArray.get(inserted_idx) > this.heapArray.get(parent_idx)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean insert(Integer data) {
        Integer inserted_idx, parent_idx; //인덱스 계산을해서 어느 노드인지 알게 하는 변수 
        
        if (this.heapArray == null) {
            this.heapArray = new ArrayList<Integer>();
            // 배열은 인덱스가 0번부터 시작하지만, 힙 구현의 편의를 위해, root 노드 인덱스 번호를 1로 지정하기 위해,
            // 0번 인덱스에는 강제로 null 을 넣어주기로 함
            this.heapArray.add(null);
            this.heapArray.add(data);
            return true;
        }

        this.heapArray.add(data);
        inserted_idx = this.heapArray.size() - 1;  //사이즈 계산 ==> 배열 => 0 1 2 3  => 4개인데 3인덱스 까지 있으므로 -1해줘야함  
        	//마지막으로 삽입된 노드는 배열 마지막 번호이니 이러한 인덱스를 가짐

        while (this.move_up(inserted_idx)) {
            parent_idx = inserted_idx / 2;
            Collections.swap(heapArray, inserted_idx, parent_idx);
            inserted_idx = parent_idx;
        }
        return true;
    }

    public boolean move_down(Integer popped_idx) {
        Integer left_child_popped_idx, right_child_popped_idx;
        //삭제되고 맨위로 올라가면, 자식 노드 왼쪽 오른쪽을 비교해서 더 큰 걸 올려야 하므로 이러한 인덱스가 필요함
         
        left_child_popped_idx = popped_idx * 2;
        right_child_popped_idx = popped_idx * 2 + 1;

        // CASE1: 왼쪽 자식 노드도 없을 때
        if (left_child_popped_idx >= this.heapArray.size()) {
            return false;
            // CASE2: 오른쪽 자식 노드만 없을 때
        } else if (right_child_popped_idx >= this.heapArray.size()) {
            if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) {
                return true;
            } else {
                return false;
            }
            // CASE3: 왼쪽, 오른쪽 자식 노드 모두 있을 때
        } else {
            if (this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)) {
                if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) { //크기 비교
                	//삭제되고 맨위로 올라가면, 자식 노드 왼쪽 오른쪽을 비교해서 더 큰 걸 올려야 하므로 이러한 인덱스가 필요함, 즉 자식 노드가 더 크면 내려가야함.  
                    return true;
                } else {
                    return false;
                }
            } else {
                if (this.heapArray.get(popped_idx) < this.heapArray.get(right_child_popped_idx)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public Integer pop() { //해당 노드 삭제 메서드
        Integer returned_data, popped_idx, left_child_popped_idx, right_child_popped_idx; 
       

        if (this.heapArray.size() <= 1) {
            return null;
        }

        returned_data = this.heapArray.get(1);
        this.heapArray.set(1, this.heapArray.get(this.heapArray.size() - 1));
        this.heapArray.remove(this.heapArray.size() - 1);
        popped_idx = 1; //뽑힌 노드는 바로 루트노드로 올라가기 때문임. (설명 onenote 참고)
        
        while (this.move_down(popped_idx)) {
            left_child_popped_idx = popped_idx * 2;
            right_child_popped_idx = popped_idx * 2 + 1;

            if (right_child_popped_idx >= this.heapArray.size()) {
                if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) {
                    Collections.swap(this.heapArray, popped_idx, left_child_popped_idx);
                    popped_idx = left_child_popped_idx; 
                }
            } else {
                if (this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)) {
                    if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) {
                        Collections.swap(this.heapArray, popped_idx, left_child_popped_idx);
                        popped_idx = left_child_popped_idx;
                    }  	
                } else {
                    if (this.heapArray.get(popped_idx) < this.heapArray.get(right_child_popped_idx)) {
                        Collections.swap(this.heapArray, popped_idx, right_child_popped_idx);
                        popped_idx = right_child_popped_idx;
                    }
                }
            }
        } 
        return returned_data;
    }

    public static void main(String[] args) {
        Heap heapTest = new Heap(15);
        heapTest.insert(10);
        heapTest.insert(8);
        heapTest.insert(5);
        heapTest.insert(4);
        heapTest.insert(20);
        System.out.println(heapTest.heapArray);

        heapTest.pop();
        System.out.println(heapTest.heapArray);
    }
}
