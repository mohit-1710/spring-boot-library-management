package com.library.dto;

public class BookWithAuthorDTO {

    private Long bookId;
    private String bookTitle;
    private String isbn;
    private Integer publicationYear;
    private String genre;
    private String authorName;
    private String authorNationality;

    public BookWithAuthorDTO(Long bookId, String bookTitle, String isbn,
                              Integer publicationYear, String genre,
                              String authorName, String authorNationality) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.authorName = authorName;
        this.authorNationality = authorNationality;
    }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getAuthorNationality() { return authorNationality; }
    public void setAuthorNationality(String authorNationality) { this.authorNationality = authorNationality; }
}
