package com.codingwithmitch.openapi.repository

import android.util.Log
import kotlinx.coroutines.Job

open class JobManager(private val className: String) {

    companion object {
        private const val TAG = "JobManager"
    }

    private val jobs: HashMap<String, Job> = HashMap()

    fun addJob(methodName: String, job: Job) {
        cancelJob(methodName)
        jobs[methodName] = job
    }

    fun cancelJob(methodName: String) {
        getJob(methodName)?.cancel()
    }

    fun getJob(methodName: String): Job? {
        jobs[methodName]?.let {
            return it
        }
        return null
    }

    fun cancelActiveJobs() {
        for ((methodName, job) in jobs) {
            Log.e(TAG, "$className: Cancelling the job for method - $methodName")
            if (job.isActive) {
                job.cancel()
            }
        }
    }


}