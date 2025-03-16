import { Component, OnInit } from '@angular/core';
import { GenreService } from '../../services/genre/genre.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Genre } from '../../model/genre.model';

@Component({
  selector: 'app-genre-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './genre-list.component.html',
  styleUrl: './genre-list.component.css'
})
export class GenreListComponent implements OnInit {
  genres: Genre[] = [];

  constructor(private genreService: GenreService) { }

  ngOnInit() {
    this.loadGenres();
  }

  loadGenres() {
    this.genreService.getGenres().subscribe(data => {
      this.genres = data;
    });
  }

  deleteGenre(id: string) {
    this.genreService.deleteGenre(id).subscribe(() => {
      this.loadGenres(); // Refresh the list
    });
  }
}
