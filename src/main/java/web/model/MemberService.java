package web.model;

import web.dao.MemberDao;
import web.entity.Member;
import web.exception.MyException;

public class MemberService {
    static MemberService instance;
    MemberDao memberDao;



    private MemberService() throws MyException {
        memberDao = new MemberDao();
    }

    public static MemberService getInstance() throws MyException {
        if(instance==null) {
            return instance = new MemberService();
        }else{
            return instance;
        }
    }

    public Member login(String id, String pw) throws MyException {

        return memberDao.login(id,pw);
    }
}
