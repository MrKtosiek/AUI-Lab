import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/book/book.service';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Book } from '../../model/book.model';
import { Genre } from '../../model/genre.model';
import { GenreService } from '../../services/genre/genre.service';

@Component({
  selector: 'app-book-details',
  imports: [RouterModule],
  templateUrl: './book-details.component.html',
  styleUrl: './book-details.component.css'
})
export class BookDetailsComponent implements OnInit {
  book: Book = {
    id: '',
    name: '',
    authorFirstName: '',
    authorLastName: '',
    popularity: 0,
    genreId: ''
  };
  genre: Genre = {
    id: '',
    name: '',
    popularity: 0
  };

  constructor(
    private bookService: BookService,
    private genreService: GenreService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.book.id = this.route.snapshot.paramMap.get('bookId')!;
    this.book.genreId = this.route.snapshot.paramMap.get('genreId')!;
    this.bookService.getBookById(this.book.id).subscribe(book => {
      this.book = book;
    });
    this.genreService.getGenreById(this.book.genreId).subscribe(genre => {
      this.genre = genre;
    });
  }
}
