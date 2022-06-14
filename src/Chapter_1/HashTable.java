package Chapter_1;

public class HashTable {
    public Slot[] hashTable;
   
    public HashTable(Integer size) {
        this.hashTable = new Slot[size]; //사이즈 할당
    }

    public class Slot {  //Slot => 배열과 비슷함
        String key; 
        String value;
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    } 
    
    
    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;  //key의 첫번째 문자값을 아스키 숫자로 변환
        //모든 주소 공간에 대한 공간 확보 해쉬테이블
    }
       
    
    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);  //해당키에 대한 주소 가져옴
        
        if (this.hashTable[address] != null) { //해당 주소에 대한 슬롯이 없을 때
            if (this.hashTable[address].key == key) {
                this.hashTable[address].value = value; //값 대입 해주면 됨
                return true;
            } else {
                Integer currAddress = address + 1;
                while (this.hashTable[currAddress] != null) {
                    if (this.hashTable[currAddress].key == key) {
                        this.hashTable[currAddress].value = value;
                        return true;
                    } else {
                        currAddress++;
                        if (currAddress >= this.hashTable.length) {
                            return false;
                        }
                    }
                }
                this.hashTable[currAddress] = new Slot(key, value);
                return true;
            }
        } else {
            this.hashTable[address] = new Slot(key, value);
        }
        return true;
    }
   
    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if (this.hashTable[address].key == key) {
                return this.hashTable[address].value;
            } else {
                // 李멸퀬: �떎�쓬 肄붾뱶瑜� �닔�젙�빀�땲�떎. 
                // Integer currAddress = address + 1;
                // �삁�쇅 耳��씠�뒪濡�, �궎�뿉 �빐�떦�븯�뒗 二쇱냼媛� 媛��옣 留덉�留� �뒳濡��씪 寃쎌슦,
                // this.hashTable[address + 1] �뿉 �빐�떦�븯�뒗 諛곗뿴�� �뾾湲� �븣臾몄뿉,
                // �삁�쇅 耳��씠�뒪�뿉�꽌�룄 �룞�옉�븯�룄濡� currAddress �뒗 address 留� ���엯�븯怨� 吏꾪뻾�빀�땲�떎
                Integer currAddress = address; // �닔�젙
                while (this.hashTable[currAddress] != null) {
                    if (this.hashTable[currAddress].key == key) {
                        return this.hashTable[currAddress].value;
                    } else {
                        currAddress++;
                        if (currAddress >= this.hashTable.length) {
                            return null;
                        }
                    }
                }
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
    	HashTable mainObject = new HashTable(20);
        mainObject.saveData("DaveLee", "01011112222");
        mainObject.saveData("fun-coding", "01022223333");
        mainObject.saveData("David", "01033334444");
        mainObject.saveData("Dave", "01044445555");

        System.out.println(mainObject.getData("DaveLee"));
        System.out.println(mainObject.getData("David"));
        System.out.println(mainObject.getData("Dave"));
    }
}