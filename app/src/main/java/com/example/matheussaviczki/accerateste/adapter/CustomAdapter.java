package com.example.matheussaviczki.accerateste.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.matheussaviczki.accerateste.R;
import com.example.matheussaviczki.accerateste.utils.Utils;


public class CustomAdapter extends BaseAdapter {

    Context context;
    String[] name;
    String[] birthDate;
    String[] description;

    private static LayoutInflater inflater;

    public CustomAdapter(Context fragment, String[] name, String[] birthDate)
    {
        this.context = fragment;
        this.name = name;
        this.birthDate = birthDate;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Itens itens;
        if(convertView == null)
        {
            itens = new Itens();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.adapt_listview, null);
            convertView.setTag(itens);

        }
        else
        {
            itens = (Itens)convertView.getTag();
        }

        itens.text = (TextView) convertView.findViewById(R.id.nome_personagem);             //Textview do nome do personagem
        itens.secondeText = (TextView) convertView.findViewById(R.id.idade_personagem);     //Textview do nascimento do personagem
        itens.text.getPaint().setShader(Utils.ReturnGradientGrey());                        //Incluindo shaders para dar gradiente
        itens.secondeText.getPaint().setShader(Utils.ReturnGradientGrey());
        itens.text.setText(name[position]);
        itens.secondeText.setText(birthDate[position]);

        return convertView;
    }

    private class Itens
    {
        //Itens que irei usar para preencher os campos da minha View do ListView
        ImageView img;
        TextView text;
        TextView secondeText;
    }
}
