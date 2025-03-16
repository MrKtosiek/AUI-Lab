import { Component, OnInit } from '@angular/core';
import { GenreService } from '../../services/genre/genre.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Genre } from '../../model/genre.model';

@Component({
  selector: 'app-genre-edit',
  imports: [FormsModule],
  templateUrl: './genre-edit.component.html',
  styleUrl: './genre-edit.component.css'
})
export class GenreEditComponent implements OnInit {
  genre: Genre = {
    id: '',
    name: '',
    popularity: 0
  }

  constructor(
    private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.genre.id = this.route.snapshot.paramMap.get('id')!;
    this.genreService.getGenreById(this.genre.id).subscribe(genre => {
      this.genre.name = genre.name;
      this.genre.popularity = genre.popularity;
    });
  }

  updateGenre() {
    this.genreService.updateGenre(this.genre).subscribe(() => {
      this.router.navigate(['/genres']);
    });
  }
}
