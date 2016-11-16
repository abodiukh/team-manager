const gulp = require('gulp');
const autoprefixer = require('gulp-autoprefixer');
const concat = require('gulp-concat');
const minify = require('gulp-minify-css');
const uglify = require('gulp-uglify');

const config = {
    build: 'src/main/webapp/resources/package',
    files: {
        fonts: [
            'node_modules/bootstrap/fonts/*'
        ],
        css: [
            'node_modules/bootstrap/dist/css/bootstrap.css',
            'src/main/webapp/resources/css/**/*.css'
        ],
        js: [
            'node_modules/jquery/dist/jquery.js',
            'node_modules/bootstrap/dist/js/bootstrap.js',
            'node_modules/angular/angular.js',
            'node_modules/angular-drag-and-drop-lists/angular-drag-and-drop-lists.js',
            'node_modules/angular-animate/angular-animate.js',
            'src/main/webapp/resources/js/**/*.js'
        ]
    }
};

gulp.task('fonts', () => {
    return gulp.src(config.files.fonts)
            .pipe(gulp.dest(config.build + '/fonts/'))
});

gulp.task('css', ['fonts'], () => {
    return gulp.src(config.files.css)
            .pipe(autoprefixer())
            .pipe(concat('main.css')) //you can add any name you want
            .pipe(minify())
            .pipe(gulp.dest(config.build + '/css/'))
});

gulp.task('js', ['css'], () => {
    return gulp.src(config.files.js)
            .pipe(uglify())
            .pipe(gulp.dest(config.build + '/js/'))
});

gulp.task('default', ['js']);