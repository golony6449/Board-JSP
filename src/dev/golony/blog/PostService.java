package dev.golony.blog;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;

public class PostService {
    private PostDao dao = null;

    PostService(){
        if (dao == null){
            dao = new PostDao();
        }
    }

    public ArrayList<PostDto> getList(){
        ArrayList<PostDto> result = dao.selectAll();

        return result;
    }

    public ArrayList<PostDto> getList(int page){
        ArrayList<PostDto> result = dao.selectAll();

        if (page <= 0 || page*10 >= result.size()){
            return null;
        }

        result = new ArrayList<>(result.subList(page*10 - 10, page*10));

        return result;
    }
}