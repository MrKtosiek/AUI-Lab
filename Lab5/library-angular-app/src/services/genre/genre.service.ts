import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Genre } from '../../model/genre.model';

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  private baseUrl = 'http://localhost:8080/api/genres'; // Adjust with gateway path

  constructor(private http: HttpClient) { }

  getGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>(`${this.baseUrl}`);
  }

  addGenre(genre: Genre): Observable<Genre> {
    return this.http.post<Genre>(`${this.baseUrl}`, genre);
  }

  getGenreById(id: string): Observable<Genre> {
    return this.http.get<Genre>(`${this.baseUrl}/${id}`);
  }

  updateGenre(genre: Genre): Observable<Genre> {
    return this.http.put<Genre>(`${this.baseUrl}/${genre.id}`, genre);
  }

  deleteGenre(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
