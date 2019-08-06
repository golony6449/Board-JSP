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

    public int getPostCount(){
        ArrayList<PostDto> temp = this.getList();
        return temp.size();
    }

    public ArrayList<PostDto> getList(int page){
        ArrayList<PostDto> result = dao.selectAll();

        if (page < 0 || page*10 >= result.size()){
            return null;
        }
        int to = (page+1)*10;
        result = new ArrayList<>(result.subList(to-10, to));

        return result;
    }

    public PostDto getPost(int id, boolean increaseHit){
        PostDto data = null;
        data = dao.selectOne(id);

        if (increaseHit){
            data.setHit(data.getHit() + 1);
            dao.update(data);
        }
        return data;
    }

    public boolean registerNewPost(String name, String title, String content, String date){
        PostDto data = new PostDto(0, name, title, content, date, 0);
        dao.insert(data);

        return true;
    }

    public boolean edit(int id, String name, String title, String content){
        PostDto data = new PostDto(id, name, title, content, "", 0);

        if (dao.update(data)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean delete(int id){
        return dao.delete(id);
    }
}
