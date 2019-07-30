package dev.golony.blog;

import java.util.ArrayList;

public class MemberService {
    private static MemberService instance = null;

    static MemberService getInstance(){
        if (instance == null){
            instance = new MemberService();
        }

        return instance;
    }

    MemberService(){

    }

    public boolean login(String id, String pw){
        ArrayList<MemberDto> list = null;
        MemberDao access = new MemberDao();

        list = access.selectAll();

        for (int i = 0; i<list.size(); i++){
            MemberDto temp = list.get(i);
            if (temp.getId().equals(id) && temp.getPw().equals(pw)){
                System.out.println("매칭된 ID, PW 존재");
                return true;
            }
        }

        System.out.printf("해당하는 ID: %s \t PW: %s 없음\n", id, pw);
        return false;
    }

    public boolean join(String name, String id, String pw){
        MemberDto newMem = new MemberDto(name, id, pw);
        MemberDao access = new MemberDao();
        boolean isSuccess = access.insert(newMem);

        return isSuccess;
//        if (isSuccess)
//            return true;
//        else
//            return false;
    }
}
