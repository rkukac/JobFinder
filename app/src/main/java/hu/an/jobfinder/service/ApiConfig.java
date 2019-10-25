package hu.an.jobfinder.service;

class ApiConfig {
    static final int TIMEOUT_CONNECT = 10000;
    static final int TIMEOUT_READ = 10000;
    static final String BASE_URL = "https://jobs.github.com/";
    static final int API_PAGINATION_MAX_ITEM = 50;
}
