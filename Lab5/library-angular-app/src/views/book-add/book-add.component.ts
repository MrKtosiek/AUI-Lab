import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/book/book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Book } from '../../model/book.model';

@Component({
  selector: 'app-book-add',
  imports: [FormsModule],
  templateUrl: './book-add.component.html',
  styleUrl: './book-add.component.css'
})
export class BookAddComponent implements OnInit {
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
    this.book.genreId = this.route.snapshot.paramMap.get('genreId')!;
  }

  addBook() {
    this.bookService.addBook(this.book).subscribe(() => {
      this.router.navigate(['/genres', this.book.genreId, 'details']);
    });
  }
}
