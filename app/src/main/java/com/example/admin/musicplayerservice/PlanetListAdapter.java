package com.example.admin.musicplayerservice;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlanetListAdapter extends RecyclerView.Adapter<PlanetListAdapter.ViewHolder> {

    List<String> planetList = new ArrayList<>();

    public PlanetListAdapter(List<String> planetList) {
        this.planetList = planetList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate(
                R.layout.planet_list_item, parent, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String planet = planetList.get( position );
        holder.planet.setText( planet );
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView planet;

        public ViewHolder(View itemView) {
            super(itemView);

            planet = itemView.findViewById( R.id.tvPlanet );
        }
    }
}
