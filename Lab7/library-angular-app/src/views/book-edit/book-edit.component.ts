import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../services/book/book.service';
import { FormsModule } from '@angular/forms';
import { Book } from '../../model/book.model';

@Component({
  selector: 'app-book-edit',
  imports: [FormsModule],
  templateUrl: './book-edit.component.html',
  styleUrl: './book-edit.component.css'
})
export class BookEditComponent implements OnInit {
  book: Book = {
    id: '',
    name: '',
    authorFirstName: '',
    authorLastName: '',
    popularity: 0,
    genreId: ''
  };

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.book.id = this.route.snapshot.paramMap.get('bookId')!;
    this.book.genreId = this.route.snapshot.paramMap.get('genreId')!;
    this.bookService.getBookById(this.book.id).subscribe(book => {
      this.book = book;
    });
  }

  updateBook() {
    this.bookService.updateBook(this.book).subscribe(() => {
      this.router.navigate(['/genres', this.book.genreId, 'details']);
    });
  }
}
