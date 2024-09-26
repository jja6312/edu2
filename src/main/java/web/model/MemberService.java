package web.model;

import web.dao.MemberDao;
import web.entity.Member;
import web.exception.MyException;

public class MemberService {
    MemberDao memberDao;

    public MemberService() throws MyException {
        memberDao = new MemberDao();
    }

    public Member login(String id, String pw) throws MyException {

        return memberDao.login(id,pw);
    }
}
