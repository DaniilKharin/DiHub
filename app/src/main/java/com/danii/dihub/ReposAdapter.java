package com.danii.dihub;


 import android.content.Context;
 import android.support.v7.widget.RecyclerView;

 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
  import android.widget.TextView;

 import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

  private  List<GithubRepo> repos;
  private  String username;
    private Context context;


 ReposAdapter(List<GithubRepo> repos, String username, Context context) {
  this.repos = repos;
  this.username=username;
     this.context=context;
 }



 @Override
 public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
 View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
 return new ViewHolder(v);
 }

 @Override
 public void onBindViewHolder(ViewHolder holder, int position) {
  GithubRepo repo = repos.get(position);
  holder.language.setText(repo.getLanguage());

  holder.updatedAt.setText((context.getResources().getString(R.string.updated).concat(repo.getUpdatedAt())));
  holder.createdAt.setText((context.getResources().getString(R.string.created).concat(repo.getCreatedAt())));
  if (repo.getOwner().getLogin().equals(username))
    holder.name.setText(repo.getName());
  else
    holder.name.setText(repo.getFullName());
  holder.description.setText(repo.getDescription());
  holder.stargazers.setText( context.getResources().getString(R.string.Star).concat(repo.getStargazersCount().toString()));
 }

 @Override
 public int getItemCount() {
 if (repos == null)
 return 0;
 return repos.size();
 }
 public final GithubRepo getItem(int position){
  return  repos.get(position) ;
 }


  public class ViewHolder extends RecyclerView.ViewHolder {
   TextView name;
   TextView description;
   TextView createdAt;
   TextView language;
   TextView updatedAt;
   TextView stargazers;

  public ViewHolder(View itemView) {
   super(itemView);
   stargazers = (TextView) itemView.findViewById(R.id.textViewStars);
   name = (TextView) itemView.findViewById(R.id.textViewName);
   createdAt = (TextView) itemView.findViewById(R.id.textViewCreated);
   updatedAt =  (TextView) itemView.findViewById(R.id.textViewUpdated);
   language =  (TextView) itemView.findViewById(R.id.textViewLanguage);
   description = (TextView) itemView.findViewById(R.id.textViewDescription);
  }


 }
 }

