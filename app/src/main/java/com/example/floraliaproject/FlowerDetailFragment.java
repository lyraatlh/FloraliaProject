package com.example.floraliaproject;

import java.util.ArrayList;

public class FlowerDetailFragment {

    public static ArrayList<Flower> getListData() {
        ArrayList<Flower> list = new ArrayList<>();
        list.add(new Flower("Aster", "Aster amellus", "Amerika Utara, Asia", "Elegance, patience, and subtle beauty", R.drawable.aster));
        list.add(new Flower("Camellia", "Camellia japonica", "Asia Timur", "Admiration, gratitude, and perfection", R.drawable.camellia));
        list.add(new Flower("Carnation", "Dianthus caryophyllus", "Eropa, Asia", "Distinction, fascination, and love", R.drawable.carnation));
        list.add(new Flower("Cherry Blossom", "Prunus serrulata", "Jepang, Asia Timur", "Beauty, transience, and fleeting life", R.drawable.cherry_blossom));
        list.add(new Flower("Chrysan", "Chrysanthemum morifolium", "Asia, Eropa Timur", "Longevity, loyalty, and joy", R.drawable.chrysan));
        list.add(new Flower("Clematis", "Clematis vitalba", "Asia, Eropa", "Creativity, mental strength, intellect", R.drawable.clematis));
        list.add(new Flower("Cymbidium", "Cymbidium", "Asia Tropis", "Luxury, grace, and respect", R.drawable.cymbidium));
        list.add(new Flower("Dahlia", "Dahlia pinnata", "Meksiko, Amerika Tengah", "Inner strength, dignity, and creativity", R.drawable.dahlia));
        list.add(new Flower("Freesia", "Freesia", "Afrika Selatan", "Purity, innocence, and trust", R.drawable.freesiawhite));
        list.add(new Flower("Gardenia", "Gardenia jasminoides", "Asia, Afrika", "Refinement, secret love, and joy", R.drawable.gardenia));
        list.add(new Flower("Iris", "Iris germanica", "Eropa, Asia, Afrika Utara", "Wisdom, valor, and faith", R.drawable.iris));
        list.add(new Flower("Jasmine", "Jasminum", "Asia Selatan, Afrika", "Grace, purity, and sensuality", R.drawable.jasmine));
        list.add(new Flower("Lavender", "Lavandula", "Mediterania", "Serenity, calmness, and devotion", R.drawable.lavender));
        list.add(new Flower("Lily", "Lilium", "Asia, Eropa, Amerika Utara", "Elegance, purity, and rebirth", R.drawable.lily));
        list.add(new Flower("Marigold", "Tagetes", "Meksiko, Amerika Selatan", "Warmth, creativity, and remembrance", R.drawable.marigold));
        list.add(new Flower("Peony", "Paeonia", "Asia Timur", "Prosperity, honor, and good fortune", R.drawable.peony));
        list.add(new Flower("Poppy", "Papaver", "Eropa, Asia, Amerika Utara", "Rest, peace, and remembrance", R.drawable.poppy));
        list.add(new Flower("Rose", "Rosa", "Asia, Eropa, Amerika", "Love, romance, and affection", R.drawable.rose));
        list.add(new Flower("Sunflower", "Helianthus annuus", "Amerika Utara", "Happiness, warmth, and loyalty", R.drawable.sunflower));
        list.add(new Flower("Tulip", "Tulipa", "Asia Tengah", "Perfect love and deep emotions", R.drawable.tulip));
        return list;
    }
}