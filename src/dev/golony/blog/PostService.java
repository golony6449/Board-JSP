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

    public PostDto getPost(int id){
        return dao.selectOne(id);
    }

    public boolean registerNewPost(String name, String title, String content, String date){
        PostDto data = new PostDto(0, name, title, content, date, 0);
        dao.insert(data);

        return true;
    }
}
