package com.example.model

data class URL(val longURL: String, val shortURL: String, val creationDate: String){
    override fun toString(): String {
        return "URL{longURL=${this.longURL},shortURL=${this.shortURL},creationDate=${this.creationDate}}"
    }
}