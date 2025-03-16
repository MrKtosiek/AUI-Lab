import { Component } from '@angular/core';
import { GenreService } from '../../services/genre/genre.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Genre } from '../../model/genre.model';

@Component({
  selector: 'app-genre-add',
  imports: [FormsModule],
  templateUrl: './genre-add.component.html',
  styleUrl: './genre-add.component.css'
})
export class GenreAddComponent {
  genre: Genre = {
    id: '',
    name: '',
    popularity: 0
  };

  constructor(private genreService: GenreService, private router: Router) { }

  addGenre() {
    this.genreService.addGenre(this.genre).subscribe(() => {
      this.router.navigate(['/genres']); // Navigate back to the list view
    });
  }
}
