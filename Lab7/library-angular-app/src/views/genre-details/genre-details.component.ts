import { Component, OnInit } from '@angular/core';
import { GenreService } from '../../services/genre/genre.service';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { BookService } from '../../services/book/book.service';
import { CommonModule } from '@angular/common';
import { Genre } from '../../model/genre.model';
import { Book } from '../../model/book.model';

@Component({
  selector: 'app-genre-details',
  imports: [CommonModule, RouterModule],
  templateUrl: './genre-details.component.html',
  styleUrl: './genre-details.component.css'
})
export class GenreDetailsComponent implements OnInit {
  genre: Genre = {
    id: '',
    name: '',
    popularity: 0
  };
  books: Book[] = [];

  constructor(
    private genreService: GenreService,
    private bookService: BookService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.genre.id = this.route.snapshot.paramMap.get('id')!;
    this.genreService.getGenreById(this.genre.id).subscribe(genre => {
      this.genre = genre;
    });
    this.loadBooks();
  }

  loadBooks() {
    this.bookService.getBooksByGenreId(this.genre.id).subscribe(books => {
      this.books = books;
    });
  }

  deleteBook(id: string) {
    this.bookService.deleteBook(id).subscribe(() => {
      this.loadBooks();
    });
  }
}
